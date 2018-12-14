function checkEquals(thistab,rows,col)
 	{
 		for(var t=col;t>0;t--)
 		{
 			if(thistab.rows[rows].cells[t-1].innerText!=thistab.rows[rows-1].cells[t-1].innerText)
 				return false;
 					
 		}
 		return true;
 	}
function   uniteTable(thistab,colLength)
{
 var rn=thistab.rows.length;//取得行数
 var rowspann=0;
 for(j=colLength-1;j>=0;j--)
 {//从第0列开始，合并多少列
  for(i=rn-1;i>0;i--)
  {//从倒数第一行开始往上检查
	  if(thistab.rows[i].cells[j].innerText==thistab.rows[i-1].cells[j].innerText
	  &&checkEquals(thistab,i,j)
	  &&thistab.rows[i].cells[j].colSpan==thistab.rows[i-1].cells[j].colSpan)
	  {
	  	
	  	  //与上面一行比较，如果两行相等就合计当前行的合并行数，并删除当前行。   
		  rowspann+=thistab.rows[i].cells[j].rowSpan;
		  thistab.rows[i].deleteCell(j);
	  }
	  else
	  {
		  thistab.rows[i].cells[j].rowSpan+=rowspann;//如果不等就完成当前相同数据的合并。
		  rowspann=0;
	  }
  }
  //检测无表头的表   
  //if(i==0&&rowspann>0){
  //	  thistab.rows[i].cells[j].rowSpan+=rowspann;//如果不等就完成当前相同数据的合并
  //	  rowspann=0;
  //}
 }
}
   
    
 try{
		HTMLElement.prototype.__defineGetter__
		(
		"innerText",
		function ()
		{
			var anyString = "";
			var childS = this.childNodes;
			for(var i=0; i<childS.length; i++)
			{
				if(childS[i].nodeType==1)
					anyString += childS[i].tagName=="BR" ? '"n' : childS[i].innerText;
				else if(childS[i].nodeType==3)
					anyString += childS[i].nodeValue;
			}
			return anyString;
		}
	); 
}
catch(e){}