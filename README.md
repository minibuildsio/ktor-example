# Ktor Example

This is an example web API written in Kotlin using the Ktor framework.

Checkout the article: https://www.minibuilds.io/posts/kotlin-ktor-example/

## Running the application

```bash
./gradlew run
```

### Making a request to the API

#### Get places

```bash
curl http://localhost:8080/places?name=mott 
```

#### Add a visit

```bash
curl -H "Content-Type: application/json" http://localhost:8080/visits \
 --data '{"placeId": "1", "visitDateTime": "2024-03-25T10:00:00"}'
```

#### Get visits

```bash
curl -s http://localhost:8080/visits
```
