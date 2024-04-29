# Hotel Management
- It allows users to register ,signIn, booking and cancellation of Hotel Application


## Features
 - CRUD operations for USER,Hotel 
 - Registation of users and Log in using JWT auth token
 - Allowing users to make a booking
 - Allows hotel manager to create, update the hotels and also to cancel the booking.
 
## Endpoints
 - POST /api/public/signup - allows a user to do registration
 - POST /api/public/signin - allows a user to do signin using credentials where we will get JWT token 
 - POST /api/private/addrole - allows a admin to add role for an another user
 - GET /api/private/hotels - gives a list of hotels
 - POST /api/private/hotels/{hotelId}/book - allows a user to book a hotel 
 - PUT /api/private/hotels/{hotelId} - allows a hotel manager to update the hotel 
 - DELETE /api/private/bookings/{bookingId} - allows a hotel manager to cancel the booking

 ## Postman Collection
- [Download Postman Collection](/postman_collection.json)