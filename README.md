# Movie-rating-application-using-Microservices

The application has three services
1.Rating data service
2.Movie info service 
3.Movie Catalog service

The Movie Catalog service is using Rating data service to get rating data for each movie based on the userId and
it uses Movie Info service to get movie information such as name, description for each user rated movie.

Technology stack:
1. SpringBoot to create REST services
2. RestTemplate to establish communication between the related services.
3. Spring cloud is used for implementing Service registery system. The Eureka Server provided by spring cloud is used to register each individual service
and Eureka client is used to publish each individual service over the discovery server provided by Eureka server. Also loadbalancing is achieved through 
Eureka server.  