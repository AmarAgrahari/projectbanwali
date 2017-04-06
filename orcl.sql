create table stock_report
(
symbol VARCHAR2(10) primary key,
ticker VARCHAR2(100),
prev_close VARCHAR2(10),
nls_volume VARCHAR2(10)
);


create or replace procedure insertuser(symbol1 in VARCHAR2,ticker1 in VARCHAR2,prev_close1 in varchar2,nls_volume1 in VARCHAR2)
is

BEGIN
  INSERT INTO STOCK_REPORT (symbol,ticker,PREV_CLOSE,NLS_VOLUME ) VALUES (symbol1,ticker1,prev_close1,nls_volume1);
EXCEPTION
  WHEN DUP_VAL_ON_INDEX THEN
    UPDATE STOCK_REPORT
    SET    ticker=ticker1,
           PREV_CLOSE=prev_close1,
           NLS_VOLUME=nls_volume1
    WHERE symbol=symbol1;
END;
/

select * from STOCK_REPORT;

delete STOCK_REPORT;