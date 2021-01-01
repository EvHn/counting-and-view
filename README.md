# counting-and-view
# Deployment to glassfish4:

Building:

${WORK_DIR}/couting-and-view> mvn install


Resourses for application:

JMS queue: jms/countingResultQueue 

JMS connection factory: jms/ConnectionFactory 


Deployment:

${GLASSFISH_DIR}/bin> asadmin start-domain

${GLASSFISH_DIR}/bin> asadmin start-database --dbhost=localhost --dbport=1527

${GLASSFISH_DIR}/bin> asadmin deploy ${WORK_DIR}/ear/target/counting-and-view.ear

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/140429ae8a0d482bb906fc137d52680f)](https://app.codacy.com/gh/EvHn/counting-and-view?utm_source=github.com&utm_medium=referral&utm_content=EvHn/counting-and-view&utm_campaign=Badge_Grade)
