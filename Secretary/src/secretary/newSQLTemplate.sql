select ID, DATE, TIME, NAME, TEXT from MAINTABLE where (DATE = (SELECT MIN(DATE) FROM MAINTABLE WHERE (DATE>='2015-6-3') AND (TIME>='21:00:00'))) AND (TIME = (Select min(time) from maintable where (TIME>='21:00:00') and (DATE>='')))