# execute this script from project root directory with command: sh local/startDb.sh
# on windows use git bash for unix syntax or manually
docker run --name libraryContainer -p 5432:5432 -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=library -d postgres

# --name self explanatory
# -e environment variables
# -d detached mode
# -p5432:5432  first is containers inner network port, second is port on localhost

