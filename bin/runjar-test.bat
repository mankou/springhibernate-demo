@echo off

echo ��������,���Ժ�....
echo path:%~dp0

::����ǰ·���л����ű�����·�� ��ʹ�����·��ʱ���õ� ���������ط����߼ƻ����������нű��п����Ҳ���·��
::��һ��ű�������bin ��jar�����ڸ�Ŀ¼�� ���������л�����Ŀ¼��
%~d0
cd %~dp0%\..
java -Dservice.env=test -jar lib/pszxjob-0.0.1-SNAPSHOT.jar 
@pause