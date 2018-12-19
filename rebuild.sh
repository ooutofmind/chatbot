#!/usr/bin/env bash
git push heroku master
heroku ps:scale worker=1