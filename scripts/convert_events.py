# Part of Escala.
# Written by Tiger Sachse.

from sys import argv as ARGS

# Constants that make up a complete SQL script.
SQL_SCRIPT = "add_events.sql"
INSERT = "INSERT INTO events VALUES ('{0}', '{1}', {2}, {3}, {4}, {5}, {6});\n"
HEADER = (
    "/*\n"
    "Part of Escala.\n"
    "Written by Tiger Sachse.\n"
    "This script has been automatically generated.\n"
    "*/\n\n"
    "CONNECT 'jdbc:derby:../data/tables';\n"
)
FOOTER = "DISCONNECT;\nExit;\n"

# Open the destination and write the header and footer, as well as individual
# INSERT lines for each argument file passed to this script.
with open(SQL_SCRIPT, "w") as destination:
    destination.write(HEADER)
    for arg in ARGS[1:]:
        with open(arg, "r") as source:
            destination.write(INSERT.format(*source.read().splitlines()))
    destination.write(FOOTER)
