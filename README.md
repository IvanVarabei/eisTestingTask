docker create -v /var/lib/postgresql/data --name PostgresData alpine


docker run -p 5433:5432 --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=insurance -d --volumes-from PostgresData postgres

docker build ./ -t springbootapp