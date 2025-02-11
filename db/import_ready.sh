#!/bin/bash
set -e  # Exit immediately if a command exits with a non-zero status

echo "Data import completed. Creating flag file..."
touch /var/lib/mysql/import_done
