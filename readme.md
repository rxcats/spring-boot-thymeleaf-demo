# Links
- https://github.com/BlackrockDigital/startbootstrap-sb-admin
- https://fontawesome.com

# Pre Installation

## Docker for mongodb
- https://hub.docker.com/_/mongo/

```
docker run -d -p 27017:27017 --restart=always --name mongo mongo
```  

## Loopback Alias for OSX
- Create an alias on the loopback interface (lo0) with the IP 192.168.99.100 on OSX

- Create /Library/LaunchDaemons/com.runlevel1.lo0.192.168.99.100.plist
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist PUBLIC "-//Apple Computer//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
<plist version="1.0">
  <dict>
      <key>Label</key>
      <string>com.runlevel1.lo0.alias</string>
      <key>RunAtLoad</key>
      <true/>
      <key>ProgramArguments</key>
      <array>
          <string>/sbin/ifconfig</string>
          <string>lo0</string>
          <string>alias</string>
          <string>192.168.99.100</string>
      </array>
      <key>StandardErrorPath</key>
      <string>/var/log/loopback-alias.log</string>
      <key>StandardOutPath</key>
      <string>/var/log/loopback-alias.log</string>
  </dict>
</plist>
```

- Set owner, mode
```
sudo chown root:wheel /Library/LaunchDaemons/com.runlevel1.lo0.192.168.99.100.plist
sudo chmod 0644 /Library/LaunchDaemons/com.runlevel1.lo0.192.168.99.100.plist
```

- Load
```
sudo launchctl load -w /Library/LaunchDaemons/com.runlevel1.lo0.192.168.99.100.plist
```

- Unload
```
sudo launchctl unload -w /Library/LaunchDaemons/com.runlevel1.lo0.192.168.99.100.plist
```
