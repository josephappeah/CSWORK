#!/bin/bash 
# Sunny Shah 
# CS265-005
# Assignment 1: Dir2XML 
# Fri Oct 16 03:43:51 EDT 2015
 

#look through the dir that was given if no dir given then usees current working directory
for path in $(find $* -type d) 
do				
	#remove temp files if they already exists 
	if [ -a $path/README_tmp ] || [ -a $path/Files_tmp ] || [ -a $path/Directories_tmp ]; then 
		rm $path/README_tmp $path/Files_tmp $path/Directories_tmp
	fi
				#create files needed 
				touch "$path/README_tmp"
				touch "$path/Files_tmp"
				touch "$path/Directories_tmp"
			    touch "$path/dir.xml"
				
				echo '<?xml version="1.0" encoding="ISO-8859-1"?>' > $path/dir.xml
				echo '<direntry>' >> $path/dir.xml
				
			
			
	#if README in current path exists then parse through it useing awk 		
	 if [ -e "$path/README" ]; then
		
				#index parse 
				for l in $(./awk "$path/README" ); do
						   if [ "$l" == index ]; then
								 echo -e '\t<index>' >> $path/dir.xml
								 continue
						   fi
						   
							echo -e "\t   <file>$l</file>" >> $path/dir.xml
							break
				done
				
				for l in $(./awk "$path/README" ); do
						   if [ "$l" == index ]; then
							   echo -e '\t<index>' >> $path/dir.xml
						   fi				
							 break
				done	
					
			   #required parse
			   for l in $(./awk "$path/README" ) ; do
			  
						if [ "$l" == index ]; then
							continue
						fi
						if [[ "$l" == *.html ]]; then
							continue
						fi
						
						if [ "$l" == required ]; then
							  echo -e '\t<required>' >> $path/dir.xml
						elif [ "$l" != required ]; then 
							  echo -e "\t   <file>$l</file>" >> $path/dir.xml  
						fi	
			  
			   done
			   
			   
			   for l in $(./awk "$path/README" ); do
					   if [ "$l" == required ]; then
							echo -e '\t<required>' >> $path/dir.xml
					   fi				
						 break
				done	
						
						 
				#parse thrugh README create REAMDE_tmp file 
				for l in $(./awk "$path/README" ); do
				
						if [ "$l" == index ]; then
							continue
						fi
						if [ "$l" == index.html ]; then
							continue
						fi
						if [[ "$l" == required ]]; then
							continue
						fi
					
						echo  $l >> $path/README_tmp
			
				done
				
				#find all Files and directoires and place into Files_tmp && Directories_tmp
				for list in $(ls $path); do 
	

						 if   [[ "$path/$list" == *_tmp ]] ; then 
							 continue
						elif  [ -f $path/$list ] ; then
							echo $list >> $path/Files_tmp
						else 
							echo $list >> $path/Directories_tmp 
						fi 
				
				done
				
			echo -e '\t<other>' >> $path/dir.xml
			
			#place other dir into dir.xml 
			for other in $(sort $path/Directories_tmp); do 
					echo -e "\t   <dir>$other</dir>" >> $path/dir.xml 
			done
			
			#place other file into dir.xml 
			for other in $(sort $path/Files_tmp  $path/README_tmp   | uniq -u); do 
	
	
					if [ "$other" == dir.xml ]; then
						continue
					fi
					if [ "$other" == index.html ]; then
						continue
					fi
					echo -e "\t   <file>$other</file>" >> $path/dir.xml 
				
				
			  done
			  
	 echo -e '\t<other>' >> $path/dir.xml
	 
	 
	fi

echo '<direntry>' >> $path/dir.xml
	
done 


#clean up files 
for path in $(find $* -type d); do
	if [ $path == CS265/Labs/2/Data ] || [ $path == ./CS265/Labs/2/Data ]; then 
		sed -i '3d' $path/dir.xml 
	fi
rm $path/README_tmp $path/Files_tmp $path/Directories_tmp
done
