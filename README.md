## Java Version of Argument Parser
[Credits: Uncle Bob Clean Code](https://github.com/unclebob/javaargs)

### Usage
Schema:
 - char    - Boolean arg.
 - char*   - String arg.
 - char#   - Integer arg.
 - char##  - double arg.
 - char[*] - one element of a string array.

Example schema: (f,s*,n#,a##,p[*])
Coresponding command line: "-f -s Bob -n 1 -a 3.2 -p e1 -p e2 -p e3