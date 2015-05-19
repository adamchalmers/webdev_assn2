import sqlite3
import os
from flask import g, Flask, request, jsonify, make_response, abort

DIRECTORY = os.path.dirname(os.path.realpath(__file__))
DATABASE = DIRECTORY + "/web_apps.db"
DEBUG = True
SECRET_KEY = 'development key'
USERNAME = 'admin'
PASSWORD = 'default'

app = Flask(__name__)
app.config.from_object(__name__)

def connect_db():
    return sqlite3.connect(app.config['DATABASE'])

def get_db():
    db = getattr(g, '_database', None)
    if db is None:
        db = g._database = connect_db()
        db.row_factory = sqlite3.Row
    return db

def query_db(query, args=(), one=False):
    cur = get_db().execute(query, args)
    rv = cur.fetchall()
    cur.close()
    return (rv[0] if rv else None) if one else rv

def encode_response(resp, code=200, msg="success", err=""):
    return make_response(
            jsonify(
                {"data"     : resp,
                 "message"  : msg,
                 "error"    : err
                    }), code)

@app.errorhandler(404)
def not_found(error):
    return encode_response("", 404, "failed", "not found")

@app.teardown_appcontext
def close_connection(exception):
    db = getattr(g, '_database', None)
    if db is not None:
        db.close()

@app.route('/')
def index():
    return "api is alive"

@app.route('/api/v1/users/authenticate', methods=['GET'])
def verify_user():
    user = request.args.get('user')
    results = query_db('select * from users where user_name = ?', (user,), one=True)
    if results is not None:
        resp = {
            "user_name" : results["user_name"],
            "admin" : [False, True][results["admin"]]
        }
        return encode_response(resp)
    else:
        abort(404)

@app.route('/api/v1/users/orders', methods=['GET'])
def get_orders():
    user = request.args.get('user')
    sql = """
        select o.*, os.order_state, sum(oi.quantity) as quantity
        from orders o 
        join order_state os ON o.order_state_id = os.order_state_id
        join order_item oi ON oi.order_id = o.order_id
        join users u ON u.user_id = o.user_id
        where u.user_name = ?
        group by o.order_id;
        """
    results = query_db(sql, (user,))
    if len(results) != 0:
        resp = [ {
                    "order_id"          : r["order_id"],
                    "address"           : r["address"],
                    "order_price"       : r["order_price"],
                    "shipping_price"    : r["shipping_price"],
                    "order_state"       : r["order_state"],
                    "quantity"          : r["quantity"]
                } for r in results]
        print (resp)
        return encode_response(resp)
    else:
        abort(404)

@app.route('/api/v1/orders/create', methods=['POST'])
def create_order():
    user = request.args.get('usr')
    user_id = query_db('select user_id from users where user_name = ?',
            (user,), one=True)
    if user_id is None:
        abort(404)

    req_keys = ["address", "order_price", "shipping_price"]
    keys, values = [], []
    for k in req_keys:
        if request.get.args(k) is not None:
            keys.append(k)
            values.append(request.get.args(k))
    keys = "user_id" + ",".join(keys)
    values = user_id + "," + ",".join(values)

    sql = """INSERT INTO orders (?) VALUES (?);"""
    db = get_db()

    db.execute(sql, (keys, values))
    order_id = db.lastrowid

    items = request.args.get('items')

    for i in items:
        pass


if __name__ == '__main__':
    app.run()
