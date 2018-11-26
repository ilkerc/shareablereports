# Sharing Reports

## Postgresql Connection

Please run report.sql script before project work

* connectionURL -> "jdbc:postgresql://localhost:5432/postgres"
* userName -> postgres
* password -> mysecretpassword

Token Expire Time: 30 sec

## Create Report

### Request

POST /rest/v1/report

```
{
	"reportTitle": "Sharing Reports",
	"reportDescription": "Rakam is an analytics platform that allows you to create your analytics services.",
	"reportAuthor":"Rakam"
}
```

### Response

```
{
    "timeStamp": 1543265979549,
    "status": 201,
    "response": {
        "id": 1543265979260,
        "reportTitle": "Sharing Reports",
        "reportDescription": "Rakam is an analytics platform that allows you to create your analytics services.",
        "reportAuthor": "Rakam"
    }
}
```

## Read Report

### Request

GET /rest/v1/report?reportId={reportId}

### Response
```
{
    "timeStamp": 1543266055802,
    "status": 200,
    "response": {
        "id": 1543265979260,
        "reportTitle": "Sharing Reports",
        "reportDescription": "Rakam is an analytics platform that allows you to create your analytics services.",
        "reportAuthor": "Rakam"
    }
}
```

## Edit Report

### Request

PUT /rest/v1/report?reportId={reportId}

```
{
	"reportTitle": "Sharing Reports",
	"reportDescription": "Rakam is an analytics platform that allows you to create your analytics services.",
	"reportAuthor":"Rakam.io"
}
```

### Response 

```
{
    "timeStamp": 1543266187765,
    "status": 200,
    "response": {
        "id": 1543264597866,
        "reportTitle": "Sharing Reports",
        "reportDescription": "Rakam is an analytics platform that allows you to create your analytics services.",
        "reportAuthor": "Rakam.io"
    }
}
```

## Delete Report

### Request

DELETE /rest/v1/report?reportId={reportId}

### Response

```
{
    "timeStamp": 1543266120459,
    "status": 204
}
```

## Share Report

### Request

POST /rest/v1/share/report

```
{
	"reportId": 1543264516509,
	"editableFields": [ "reportTitle", "reportDescription"]
}
```

### Response

```
{
    "timeStamp": 1543266374813,
    "status": 201,
    "response": {
        "resourceURL": "/rest/v1/share/report?reportId=1543264516509",
        "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlZGl0YWJsZU9wdGlvbnMiOlsicmVwb3J0VGl0bGUiLCJyZXBvcnREZXNjcmlwdGlvbiJdLCJyZXBvcnRJZCI6MTU0MzI2NDUxNjUwOSwiaXNzIjoiYXV0aDAiLCJleHAiOjE1NDMyNjY0MDMsImlhdCI6MTU0MzI2NjM3M30.wiZqagc_WvfRDrddHQLYwn85oQlTiq-csrdSkmUGgxE"
    }
}
```

## Read Shared Report

### Request 

GET /rest/v1/share/report?reportId={reportId}

Header Authorization Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlZGl0YWJsZU9wdGlvbnMiOlsicmVwb3J0VGl0bGUiLCJyZXBvcnREZXNjcmlwdGlvbiJdLCJyZXBvcnRJZCI6MTU0MzI2NDUxNjUwOSwiaXNzIjoiYXV0aDAiLCJleHAiOjE1NDMyNjY2NjQsImlhdCI6MTU0MzI2NjYzNH0.UlurYfgl0z6HkoRtDoHSQ_YJMrv6aC3-6XJ_Ja7-RJs

### Response

```
{
    "timeStamp": 1543266650606,
    "status": 200,
    "response": {
        "id": 1543264516509,
        "reportTitle": "Title",
        "reportDescription": "Description",
        "reportAuthor": "Author"
    }
}
```

## Edit Shared Report

### Request

PUT /rest/v1/share/report?reportId={reportId}

Header Authorization Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlZGl0YWJsZU9wdGlvbnMiOlsicmVwb3J0VGl0bGUiLCJyZXBvcnREZXNjcmlwdGlvbiJdLCJyZXBvcnRJZCI6MTU0MzI2NDUxNjUwOSwiaXNzIjoiYXV0aDAiLCJleHAiOjE1NDMyNjY2NjQsImlhdCI6MTU0MzI2NjYzNH0.UlurYfgl0z6HkoRtDoHSQ_YJMrv6aC3-6XJ_Ja7-RJs

{
	"reportAuthor":"author",
    "reportTitle":"title",
    "reportDescription":"description"
}

### Response

```
{
    "timeStamp": 1543266939753,
    "status": 200,
    "response": {
        "id": 1543184179103,
        "reportTitle": "title",
        "reportDescription": "description",
        "reportAuthor": "author"
    }
}
```

```
{
    "timeStamp": 1543266803127,
    "status": 403,
    "message": "The Token's Signature resulted invalid when verified using the Algorithm: HmacSHA256"
}
```

```
{
    "timeStamp": 1543266777709,
    "status": 403,
    "message": "The Token has expired on Tue Nov 27 00:11:04 EET 2018."
}
```
