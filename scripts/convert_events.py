from sys import argv as ARGS

SQL_FILE = "add_events.sql"
INSERT = "INSERT INTO events VALUES ('{0}', '{1}', {2}, {3}, {4}, {5}, {6});\n"
HEADER = ("/*\n"
         "Part of Escala.\n"
         "Written by Tiger Sachse.\n"
         "*/\n\n"
         "CONNECT 'jdbc:derby:../data/tables';\n")
FOOTER = "DISCONNECT;\nExit;\n"

with open(SQL_FILE, "w") as destination:
    destination.write(HEADER)
    for arg in ARGS[1:]:
        with open(arg, "r") as source:
            destination.write(INSERT.format(*source.read().splitlines()))
    destination.write(FOOTER)
