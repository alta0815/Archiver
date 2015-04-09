# Archiver
utility providing zip/unzip functionality
###Run in cmd
    java -jar Archiver-1.0.jar some commands
###Zip functionality
* -zip VAL        : set name for zipFile
* -files STRING[] : add files to zip
* -add VAL        : add comment to archiver(exist or non exist) !!!depends -files
* -read           : read comment (default: false)
    
####Examples zip
    java -jar Archiver-1.0.jar -zip fileName.zip -files src .gitignore 
    java -jar Archiver-1.0.jar -zip fileName.zip -files src .gitignore -add "Some comment"
    java -jar Archiver-1.0.jar -zip fileName.zip -files src .gitignore -add "Some comment" -read
    java -jar Archiver-1.0.jar -zip fileName.zip -files src .gitignore -read
    java -jar Archiver-1.0.jar -zip fileName.zip -read
###UnZip functionality
* -unzip VAL      : set name for unzipFile
* -path VAL       : set path folder to unzip(default is folder with name
                   "zipFileName" in WORK_DIRECTORY)
* -read           : read comment (default: false)

####Examples unzip
    java -jar Archiver-1.0.jar -unzip fileName.zip
    java -jar Archiver-1.0.jar -unzip fileName.zip -path /home/<username>/FolderForUnzip
    java -jar Archiver-1.0.jar -unzip fileName.zip -path /home/<username>/FolderForUnzip -read
    java -jar Archiver-1.0.jar -unzip fileName.zip -read
