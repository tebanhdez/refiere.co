After clonning this repo do:

npm install

bower install

for installing the dependencies.

For running the server locally con Mac OS, Linux o any other Unix like system run:
heroku local

For running the server in Windows systems run:
heroku local web -f procfile.windows

After the app is up and running go to your browser (Mozilla, Chrome, Safari)
and in the directions bar write:
localhost:5000
And the app should show up in your browser.

For building the project using maven:
mvn package

For building the project using maven but without tests run:
mvn package -Dmaven.test.skip=true

In windows:
mvn package "-Dmaven.test.skip=true"

To create a clean database go to src/main/resources/hibernate.cfg.xml and
change in line 21 "validate" for "create", run the project once and change
it again to "validate".

<property name="hibernate.hbm2ddl.auto">create</property>

Then change it again for:

<property name="hibernate.hbm2ddl.auto">validate</property>

If you don't do this last step, every time you will run the app the database
will be erased.
