#!/bin/bash -x

curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X POST -d @samples/blog-post.json http://localhost:8000/payload-tests/blog
echo "---------------------------------------------------------------------------------------------------------------------------------" &> /dev/null
curl -i -H "Accept: application/xml" -H "Content-Type: application/xml" -X POST -d @samples/blog-post.xml http://localhost:8000/payload-tests/blog

