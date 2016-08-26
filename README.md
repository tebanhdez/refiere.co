Refiere.co
===================

Required software
-------------
 1. Maven
 2. NodeJs
 3. Bower
 4. Postgres

Setup development environment
-------------

 1. Create database in postgres:

 2. Clone repo:  
 `git clone https://github.com/tebanhdez/refiere.co`
  
 3. Install node dependencies:  
`npm install`

 4. Install bower dependencies:  
`bower install`

Build the project
-------------

 - Build the project using maven command:  
`mvn package`

 - Build the project using maven but without tests run:   
`mvn package -Dmaven.test.skip=true`

- In windows:   
`mvn package "-Dmaven.test.skip=true"`

> Note: *To create a clean database go to
> src/main/resources/hibernate.cfg.xml and change in line 21 "validate"
> for "create", run the project once and change it again to "validate".*  
> 
> `<property name="hibernate.hbm2ddl.auto">create</property>`  
Then change it again for:
> 
> `<property name="hibernate.hbm2ddl.auto">validate</property>`  
> *If you don't do this last step, every time you will run the app the database will be erased.*


Run in localhost
-------------------

### MacOS 
For running the server locally con Mac OS, Linux o any other Unix like system run:  
`heroku local`


### Windows  
For running the server in Windows systems run:    
`heroku local web -f procfile.windows`

### Open in web browser  
After the app is up and running go to your browser (Mozilla, Chrome, Safari) and in the directions bar write:    
`localhost:5000`  

And the app should show up in your browser.


----------
Crafted by:  
[![Pernix-Solutions.com](http://pernix.cr/static/images/pernix-logo.svg)
](http://Pernix-Solutions.com)