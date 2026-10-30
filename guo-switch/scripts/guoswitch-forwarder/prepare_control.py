from pathlib import Path
import sys

path = Path(sys.argv[1])
data = bytearray(path.read_bytes())
if len(data) != 0x4000:
    raise SystemExit("Invalid NACP size")
data[0x3025] = 0
data[0x3026] = 0
path.write_bytes(data)
