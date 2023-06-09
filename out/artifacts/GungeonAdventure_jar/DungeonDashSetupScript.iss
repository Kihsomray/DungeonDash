; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

#define MyAppName "Dungeon Dash"
#define MyAppVersion "1.0"
#define MyAppPublisher "Goofy Studio LLC"
#define MyAppURL "https://dev.zerotoil.net/"
#define MyAppExeName "DungeonDash.bat"

[Setup]
; NOTE: The value of AppId uniquely identifies this application. Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{39A57A23-51A3-4827-97B4-4FB7C732DE99}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
;AppVerName={#MyAppName} {#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
AppSupportURL={#MyAppURL}
AppUpdatesURL={#MyAppURL}
DefaultDirName={autopf}\{#MyAppName}
DisableProgramGroupPage=yes
; Uncomment the following line to run in non administrative install mode (install for current user only.)
;PrivilegesRequired=lowest
OutputDir=E:\Patrick\College\UWT\Quarter 6\Software Development\DungeonAdventure\out\artifacts\GungeonAdventure_jar
OutputBaseFilename=DungeonDash
SetupIconFile=E:\Patrick\College\UWT\Quarter 6\Software Development\DungeonAdventure\out\artifacts\GungeonAdventure_jar\dungeondash.ico
Compression=lzma
SolidCompression=yes
WizardStyle=modern

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "E:\Patrick\College\UWT\Quarter 6\Software Development\DungeonAdventure\out\artifacts\GungeonAdventure_jar\{#MyAppExeName}"; DestDir: "{app}"; Flags: ignoreversion
Source: "E:\Patrick\College\UWT\Quarter 6\Software Development\DungeonAdventure\out\artifacts\GungeonAdventure_jar\DungeonCharacters.db"; DestDir: "{app}"; Flags: ignoreversion
Source: "E:\Patrick\College\UWT\Quarter 6\Software Development\DungeonAdventure\out\artifacts\GungeonAdventure_jar\GungeonAdventure.jar"; DestDir: "{app}"; Flags: ignoreversion
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{autoprograms}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"
Name: "{autodesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; Tasks: desktopicon

[Run]
Filename: "{app}\{#MyAppExeName}"; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"; Flags: shellexec postinstall skipifsilent

