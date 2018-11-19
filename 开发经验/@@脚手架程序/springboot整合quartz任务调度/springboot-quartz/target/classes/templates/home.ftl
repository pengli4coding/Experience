<html>
	<head>
		<title>展示用户数据</title>
		<meta charset="utf-8"></meta>
	</head>
	
	<body>
		
		<table border="1" align="center" width="50%">
			
			<tr>
				<th>TEXT</th>
			</tr>
			
			<#list list as text >
				<tr>
					<td>${text}</td>
				</tr>
			</#list>	
		</table>
	</body>
</html>