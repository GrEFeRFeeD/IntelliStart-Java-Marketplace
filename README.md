# IntelliStart-Java-Marketplace
Practical test challenge

## Endpoints
<ol>
  <li>[GET] /users - provides list of existing users</li>
  <li>[GET] /users?productId={id} - provides list of users which have purchased product by given id</li>
  <li>[GET] /products - provides list of existing products</li>
  <li>[GET] /products?userId={id} - provides list of products which have been purchased by user specified with given id</li>
  <li>[POST] /users - adding new user to system</li>
  <li>[POST] /users/{id}/purchase - purchasing a product with id given in request body by user specified with given via path id</li>
  <li>[POST] /products - adding new product to system</li>
  <li>[DELETE] /users/{id} - deleting user with given id from the system</li>
  <li>[DELETE] /products/{id} - deleting product with given id from the system</li>
  
  <li>(extra) [GET] /users?productId={id}&withCount - provides list of users which have purchased product by given id with counting</li>
  <li>(extra) [GET] /products?userId={id}&withCount - provides list of products with counting which have been purchased by user specified with given id</li>
  
</ol>

## Used technologies
Java, Spring (Boot, Data, Web), Hibernate, PostreSQL

## Deployment
Deployed at: https://marketplace-test-intellistart.herokuapp.com/
