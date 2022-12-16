<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>File Upload to Database</title>
</head>
<body>
<h1>File Upload to Database</h1>
	
        <form method="post" action="UploadCSV" enctype="multipart/form-data">
            <table border="0">
                <tr>
                    <td>CSV File: </td>
                    <td><input type="file" name="csv" size="50"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Save">
                    </td>
                </tr>
            </table>
        </form>
        
        
</body>
</html>