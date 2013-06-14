rem mvn archetype:create -DgroupID=com.angelo -DartifactId=LoggerAnalysis

goto comments
	vi .gitignore
	
	git add .
	git add -u 
	git commit -m "--"
	git push
:comments

java -classpath ../lib/*;  -Xmx512m com.angelo.App init


goto comments
	BACKUP TO 'backup.zip'	
	CALL CONCAT (
		CONCAT(
		'',
		SELECT CONCAT('BACKUP_', REPLACE(NOW(), ':', '-'))
		),
	'.zip');
	
	BACKUP TO ''
:comments