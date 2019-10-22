#!/bin/sh
exec < /dev/tty
while true; do
  read -p "Are you sure you have tested the application with proguard/r8 enabled (y/n)? " answer
  if [ "$answer" != "${answer#[Yy]}" ] ;then
    echo "Good job"
    break
  else
    echo "Please test it before commit your changes"
    exit 1
  fi
done

echo "Running static analysis..."
./gradlew detekt --rerun-tasks

status=$?

if [ "$status" = 0 ] ; then
    echo "Static analysis found no issues. Proceeding with commit."
    exit 0
else
    echo 1>&2 "Static analysis found issues you need to fix before commit."
    exit 1
fi
