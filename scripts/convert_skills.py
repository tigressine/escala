# Part of Escala.
# Written by Tiger Sachse.

from sys import argv as ARGS

# Constants that make up a complete SQL script.
SQL_SCRIPT = "add_skills.sql"
INSERT_NODE = (
    "INSERT INTO skill_nodes VALUES"
    " ('{0}', {1}, '{2}', '{3}', {4}, {5}, {6}, {7});\n"
)
INSERT_EDGE = "INSERT INTO skill_edges VALUES ('{0}', {1}, {2});\n"
HEADER = (
    "/*\n"
    "Part of Escala.\n"
    "Written by Tiger Sachse.\n"
    "This script has been automatically generated.\n"
    "*/\n\n"
    "CONNECT 'jdbc:derby:../data/tables';\n"
)
FOOTER = "\nDISCONNECT;\nExit;\n"

class Node:
    """Hold information about graph nodes."""
    def __init__(self, tree,
                 identifier,
                 targets, name,
                 description, cost,
                 logistics_effect,
                 marketing_effect,
                 efficiency_effect):
        """Initialize the graph node."""

        self.tree = tree
        self.identifier = int(identifier)
        self.targets = [int(target) for target in targets.split()]
        self.name = name
        self.description = description
        self.cost = float(cost)
        self.logistics_effect = logistics_effect
        self.marketing_effect = marketing_effect
        self.efficiency_effect = efficiency_effect


    def get_node_insertion_command(self):
        """Format an SQL insertion command with the appropriate node information."""
        return INSERT_NODE.format(
            self.tree,
            self.identifier,
            self.name,
            self.description,
            self.cost,
            self.logistics_effect,
            self.marketing_effect,
            self.efficiency_effect
        )


    def get_edge_insertion_commands(self):
        """Format SQL insertion commands with appropriate node edge information."""
        commands = []
        if self.targets[0] != -1:
            for target in self.targets:
                commands.append(
                    INSERT_EDGE.format(self.tree, self.identifier, target)
                )
        return commands


def chunk(items, size):
    """Return chunks of a specified size of a list."""
    for start in range(0, len(items), size):
        yield items[start:start + size]


# Open a destination SQL script and write SQL commands to it.
with open(SQL_SCRIPT, "w") as destination:
    destination.write(HEADER)

    # Add information from all provided plaintext files to the destination
    # SQL script.
    for arg in ARGS[1:]:
        destination.write("\n")

        # Open the source, parse it, create Node objects, and write insertion
        # commands for the destination SQL script.
        with open(arg, "r") as source:
            lines = [item for item in source.read().splitlines() if item is not ""]
            tree_id = lines.pop(0)
            nodes = [Node(tree_id, *parameters) for parameters in chunk(lines, 8)]
            for node in nodes:
                destination.write(node.get_node_insertion_command())
            destination.write("\n")
            for node in nodes:
                destination.writelines(node.get_edge_insertion_commands())

    # Add a footer at the end of the file.
    destination.write(FOOTER)
