from pathlib import Path
import sys

path = Path(sys.argv[1])
source = path.read_bytes()
if not source.startswith(b"\xff\xd8"):
    raise SystemExit("Invalid JPEG header")
output = bytearray(source[:2])
position = 2
while position < len(source):
    if source[position] != 0xff:
        raise SystemExit("Invalid JPEG segment")
    marker = source[position + 1]
    if marker in (0xda, 0xd9):
        output.extend(source[position:])
        break
    length = int.from_bytes(source[position + 2:position + 4], "big")
    end = position + 2 + length
    if length < 2 or end > len(source):
        raise SystemExit("Invalid JPEG segment size")
    if marker != 0xe1:
        output.extend(source[position:end])
    position = end
path.write_bytes(output)
