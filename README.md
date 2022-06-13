# liquibase-custom-gui
This is a custom web based GUI for Liquibase integrated with Spring Boot Application and H2 Database.

This GUI has been created additionally for running the liquibase update operation from UI instead of it's default behaviour of getting triggered at server start. Also, this UI provides a feature to generate a unique changeset id which developers can use in the changelog files. This would 100% ensure the uniqueness of changeset ids across all the developers.

## Service Start Up Logs

![Service Logs](screenshots/1_custom_liquibase_gui.png?raw=true "Liquibase Customm GUI")