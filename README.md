# Kasikorn Line - Assignment test
## Setup Guide
1. Download Git LFS from [here](https://git-lfs.com/) **(if cannot use Git LFS go to step 2.)**
1.1 Open CMD or PowerShell after installed `.exe`
1.2 Run `git lfs install`
1.3 Run `git clone https://github.com/thebear19/assignment.git`
1.4 Go to `assignment/db` folder
1.5 Extract `data.zip` in the `assignment/db`
1.6 Go to **step 3.**
2. Install database manually by download `mock.zip` in this [google drive](https://drive.google.com/drive/folders/1m0nXqhuZ_JdYzJBLCT-5DtbHZ4YU7HMQ)
2.1 Extract file in `assignment/db`
2.2 Rename all SQL file by adding number prefix with under scroll `(ex. filename.sql -> 01_filename.sql)`
2.2.1 Make sure `import_ready.sh` script is the last file in the folder
3. Go back to root folder `/assignment`
4. Open CMD or PowerShell here and run `docker compose up`
5. Wait the installation around 15 min
6. Now, you access with http://localhost
7. Tying pin `123456` to login

## Project Structure Overview

### Front-end

    assignment-fe
    |
    +-- deployment	       # nginx config (e.g. reverse proxy)
    |
    +-- public	       # contains index.html
    |
    +-- src                # application layer containing
    |   |
    |   +-- assets         # use to store static files
    |   |
    |   +-- component      # shared components used across the entire application
    |   |
    |   +-- features       # feature based modules
    |   |   |
    |   |   +-- dashboard  # feature name
    |   |   |
    |   |   +-- component  # components used only this feature
    |   |   |
    |   |   +-- services   # services used to communicate with back-end API
    |   |   |
    |   |   +-- type       # types used only this feature
    |   |
    |   +-- app.tsx        # main application component and contain routing
    |   |
    |   +-- utils          # shared utility functions
    |
    +-- .env               # application variable config based on run-time environment

### Back-end

    assignment-bff
    |
    +-- src
    |   |
    |   +-- main
    |   |   |
    |   |   +-- java                 # application layer containing
    |   |   |   |
    |   |   |   +-- account          # feature name
    |   |   |   |   |
    |   |   |   |   +-- api          # controller interface
    |   |   |   |   |
    |   |   |   |   +-- controller   # controller implementation
    |   |   |   |   |
    |   |   |   |   +-- mapper       # interface use for map data type to others type
    |   |   |   |   |
    |   |   |   |   +-- model        # data type for this feature
    |   |   |   |   |   |
    |   |   |   |   |   +-- dto      # data transfer object (data type for communicate with front-end)
    |   |   |   |   |
    |   |   |   |   +-- repository   # contain DB interface and data type
    |   |   |   |   |   |
    |   |   |   |   |   +-- dao      # data access object (data type for communicate with DB)
    |   |   |   |   |   |
    |   |   |   |   |   +-- entity   # persistence domain object (represents a table in a DB)
    |   |   |   |   |
    |   |   |   |   +-- service      # feature business logic
    |   |   |   |
    |   |   |   +-- common           # shared feature
    |   |   |   |
    |   |   |   +-- exception        # shared error handler
    |   |   |
    |   |   +-- resources            # application config based on environment
    |   |
    |   +-- test                     # contain unit tests

## Back-end API document
- http://localhost:8081/swagger-ui/index.html
**Remark: Need to start the application first.**