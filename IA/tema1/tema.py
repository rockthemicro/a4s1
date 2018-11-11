# -*- coding: utf-8 -*-

import yaml

with open("test_input.yml", 'r') as stream:
    data_loaded = yaml.load(stream)

print(data_loaded)