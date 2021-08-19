#!/bin/bash
set -euo pipefail

#create folders for docker container
mkdir -p mongo-db-data
mkdir -p production-logs

#run docker compose to bring up containers
docker-compose up | tee app.log || failed=yes
docker-compose logs --no-color > docker-compose.log
[[ -z "${failed:-}" ]] || exit 1