docker run --name libraryDb -e POSTGRES_PASSWORD=postgres POSTGRES_USER=postrgres -d -p5432:5432 postgres

# --name self explanatory
# -e environment variables
# -d detached mode
# -p5432:5432  first is containers inner network port, second is port on localhost

