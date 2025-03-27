/^#/        { print; next }
$5 == "<INS>" && rand() < 50 / 12840    { print; next }
$5 == "<INV>" && rand() < 50 / 78       { print; next }
$5 == "<DUP>" && rand() < 50 / 32       { print; next }
$5 == "<DEL>" && rand() < 50 / 9144     { print; next }
