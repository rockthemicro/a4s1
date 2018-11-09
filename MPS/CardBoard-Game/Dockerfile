FROM ruby:2.5.1-stretch

RUN apt-get update && \
	apt-get -yq install sqlite3 && \
	rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

ADD . /var/app

WORKDIR /var/app

RUN mkdir -p ./log
RUN mkdir -p ./tmp/pids

RUN bundle install
