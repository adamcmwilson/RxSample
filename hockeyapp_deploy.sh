#!/bin/sh

curl \
  -F "status=2" \
  -F "notify=1" \
  -F "ipa=@app/build/outputs/apk/app-debug.apk" \
  -H "X-HockeyAppToken: $HOCKEYAPP_TOKEN" \
  https://rink.hockeyapp.net/api/2/apps/09014d8499174558a03c03729f098094/app_versions/upload
