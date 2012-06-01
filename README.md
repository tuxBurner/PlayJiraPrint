PlayJiraPrint
=============

This is a simple web application which lets you print your Jira-Issues with the image attachements.

What it does
------------

If you have a Jira-Issue with images as attachements, there is no way to print the images bigger than the thumbnails.

This web application is written with the http://www.playframework.org/ and uses the Jira-Rest-Api to gather the informations and displays a simple webpage you can print.

Just fill out the form and hit the print button :)

Change default jira url
-----------------------

In the application.conf you can change the jira.default.url entry for prefill the Jira url in the form.

Todos
-----

* Implement a css for @print
* Add a print js button so the user dont has to click in the browsers menu.
* Add printing templates, so the user can select how the page for printing should look.
* Add subtasks to print with the main task.
* Add JQL support to print multiple tasks at once.
