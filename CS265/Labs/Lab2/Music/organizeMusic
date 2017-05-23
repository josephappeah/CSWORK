#!/bin/bash

for f in *.mp3; do

dash=$(expr index "$f" "-");

artist=${f:0:$dash-1};
song=${f:$dash+1};

cp "$f" "$song";


 if [ ! -d "$artist" ];
	 then	
		 mkdir "$artist"
 fi

 mv "$song" "$artist"; 

done

