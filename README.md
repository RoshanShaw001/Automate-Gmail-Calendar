In this project automated Google Calendar to create, delete and schedule Tasks on the Calendar using and explored methods of having a signed google chrome Instance.

TestCase01: Verify Calendar Home Page
  Description: Verify the URL of the Google Calendar home page
  Test Steps:
  Navigate to Google Calendar ( https://calendar.google.com/ ).
  Verify that the current URL contains "calendar."
  Expected Result: The URL of the Calendar homepage contains "calendar".
  
TestCase02: Verify Calendar Navigation and Add Task
  Description: Switch to the month view, and add a task for today.
  Test Steps:
  Switch to the month view.
  Click on the calendar to add a task.
  Navigate to the Tasks tab.
  Select a date and enter task details. Task Details:
  Title: Crio INTV Task Automation
  Description: Crio INTV Calendar Task Automation
  Expected Result: The Calendar switched to month view and a task was created.
  
TestCase03: Verify the Task Updation
  Description: Select an existing task, update its description, and verify the successful update.
  Test Steps:
  Click on an existing task.
  Open the task details.
  Update the task description to "Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application"
  Verify that the updated description is displayed.
  Expected Result: The task description is successfully updated and displayed.
  
TestCase04: Verify the Task deletion
  Description: Delete an existing task and confirm the deletion.
  Test Steps:
  Click on an existing task.
  Open the task details.
  Verify the title of the task.
  Delete the task.
  Confirm the task deletion, by verifying "Task deleted" is displayed.
  Expected Result: The task is successfully deleted, and the confirmation message indicates "Task deleted"
