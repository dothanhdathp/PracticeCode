import pip
import importlib
import matplotlib.font_manager

modules = ['matplotlib']
for modname in modules:
    try:
        # try to import the module normally and put it in globals
        globals()[modname] = importlib.import_module(modname)
    except ImportError as e:
        result = pip.main(['install', modname])
        if result != 0: # if pip could not install it reraise the error
            raise
        else:
            # if the install was sucessful, put modname in globals
            globals()[modname] = importlib.import_module(modname)

import matplotlib.font_manager

flist = matplotlib.font_manager.get_fontconfig_fonts()
names = [matplotlib.font_manager.FontProperties(fname=fname).get_name() for fname in flist]
print(names)
