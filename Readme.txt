Unfortunately this suite is only partially complete due to the provided time constraints. To run multiple tests,
run via the testng.xml file. Due to TestNG not loading the DTD of unsecured Urls by default, all run configurations
will require the following VM options:
-ea -Dtestng.dtd.http=true

Typically I would include additional layers and more OO principles in my design, with things such as a BDD layer, reduced
code duplication through grouping of functions into common methods, or creating utility classes where required. I would
also handle data input via some kind of file or DB IO, dependant upon requirements.

Part way through development I began to realise the the PageFactory pattern was actually not helping with this particular
site, however it was too late to revert to using a standard Page Object Model pattern, which would have given tighter
control over element wait times.