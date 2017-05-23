#!/bin/bash

for file in *; do 

lines=$(wc -l "$file");
words=$(wc -w "$file");

echo "$file" "${lines%%"$file"}" "${words%%"$file"}";

done;
