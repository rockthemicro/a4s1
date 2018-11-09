#!/bin/bash

# ARGV processing
for i in "$@"
do
case $i in
  setup)
    COMMAND='setup'
  ;;
  start)
    COMMAND='start'
  ;;
  connect)
    COMMAND='connect'
  ;;
  stop)
    COMMAND='stop'
  ;;
  *)
    echo -e "Available commands: \033[0;32msetup, start, connect, stop\033[0m."
  ;;
esac
done

# Setup
if [ "$COMMAND" = "setup" ];
then
  RUN="docker image rm card_board_game --force; docker build -t card_board_game:latest ."
# Start
elif [ "$COMMAND" = "start" ];
then
  RUN="docker run --rm --name gonoi -d -t -p 3000:3000 --mount src="$(pwd)",target=/var/app,type=bind card_board_game /bin/sh; docker exec -it gonoi bundle install; docker exec -it gonoi bundle exec puma -C ./config/puma.rb --daemon"
elif [ "$COMMAND" = "connect" ];
then
  RUN="docker exec -it gonoi bash"
elif [ "$COMMAND" = "stop" ];
then
  RUN="docker stop gonoi"
fi

eval $RUN
