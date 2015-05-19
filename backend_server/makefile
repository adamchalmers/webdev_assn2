
init-db: backend-schema.sql
	echo ".exit" | sqlite3 -init backend-schema.sql web_apps.db

web_apps.db: backend-schema.sql
	make init-db

open-db: web_apps.db
	sqlite3 web_apps.db
