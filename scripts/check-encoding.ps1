param(
    [string]$Root = (Split-Path -Parent $PSScriptRoot)
)

$patterns = @(
    ([string][char]0xFFFD),
    ([string][char]0x9422 + [string][char]0x9366 + [string][char]0x57DB),
    ([string][char]0x7F02 + [string][char]0x682B),
    ([string][char]0x7487 + [string][char]0x70BD),
    ([string][char]0x9225 + [string][char]0x6A25)
)

$targets = Get-ChildItem -Path $Root -Recurse -File | Where-Object {
    $_.FullName -match '\\src\\main\\(java|resources)\\|\\docs\\|\\sql\\'
}

$hits = @()
foreach ($file in $targets) {
    $text = Get-Content $file.FullName -Raw -Encoding UTF8
    foreach ($pattern in $patterns) {
        if ($text.IndexOf($pattern) -ge 0) {
            $hits += [pscustomobject]@{ File = $file.FullName; PatternCode = (($pattern.ToCharArray() | ForEach-Object { 'U+{0:X4}' -f [int]$_ }) -join ' ') }
            break
        }
    }
}

if ($hits.Count -gt 0) {
    $hits | Format-Table -AutoSize
    exit 1
}

Write-Host 'No encoding issues found.'

