package com.neuedu.xinxin.util;

import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;

public final class SQLUtils {
	// SQL关键字列表
	private static List<String> SQL_KEYWORDS = new ArrayList<String>(300);
	// SQL运算符号及特殊字符列表
	private static List<String> SQL_CHARS = new ArrayList<String>();
	// SQL关键字数量
	private static int SQL_KEYWORDS_COUNT = 0;
	// SQL运算符号及特殊字符数量
	private static int SQL_CHARS_COUNT = 0;
	static {
		SQL_CHARS.add("'");
		SQL_CHARS.add("#");
		SQL_CHARS.add("*");
		// SQL_CHARS.add("_");
		SQL_CHARS.add("?");
		SQL_CHARS.add("{");
		SQL_CHARS.add("}");
		SQL_CHARS.add(";");
		SQL_CHARS.add("(");
		SQL_CHARS.add(")");
		SQL_CHARS.add("+");
		SQL_CHARS.add("-");
		SQL_CHARS.add("*");
		SQL_CHARS.add("$");
		SQL_CHARS.add("%");
		//SQL_CHARS.add("/");
		SQL_CHARS.add("=");
		SQL_CHARS.add("!=");
		SQL_CHARS.add("<>");
		SQL_CHARS.add(">");
		SQL_CHARS.add("<");
		SQL_CHARS.add(">=");
		SQL_CHARS.add("<=");
		SQL_CHARS.add("!<");
		SQL_CHARS.add("!>");
		SQL_CHARS.add("!>");
		SQL_CHARS.add("\"");
		SQL_CHARS.add(";");
		SQL_KEYWORDS.add("add");
		SQL_KEYWORDS.add("analyze");
		SQL_KEYWORDS.add("analyze");
		SQL_KEYWORDS.add("between");
		SQL_KEYWORDS.add("blob");
		SQL_KEYWORDS.add("call");
		SQL_KEYWORDS.add("change");
		SQL_KEYWORDS.add("check");
		SQL_KEYWORDS.add("condition");
		SQL_KEYWORDS.add("continue");
		SQL_KEYWORDS.add("cross");
		SQL_KEYWORDS.add("current_timestamp");
		SQL_KEYWORDS.add("database");
		SQL_KEYWORDS.add("day_microsecond");
		SQL_KEYWORDS.add("dec");
		SQL_KEYWORDS.add("default");
		SQL_KEYWORDS.add("desc");
		SQL_KEYWORDS.add("distinct");
		SQL_KEYWORDS.add("double");
		SQL_KEYWORDS.add("each");
		SQL_KEYWORDS.add("enclosed");
		SQL_KEYWORDS.add("exit");
		SQL_KEYWORDS.add("fetch");
		SQL_KEYWORDS.add("float8");
		SQL_KEYWORDS.add("foreign");
		SQL_KEYWORDS.add("goto");
		SQL_KEYWORDS.add("having");
		SQL_KEYWORDS.add("hour_minute");
		SQL_KEYWORDS.add("ignore");
		SQL_KEYWORDS.add("infile");
		SQL_KEYWORDS.add("insensitive");
		SQL_KEYWORDS.add("int1");
		SQL_KEYWORDS.add("int4");
		SQL_KEYWORDS.add("interval");
		SQL_KEYWORDS.add("iterate");
		SQL_KEYWORDS.add("keys");
		SQL_KEYWORDS.add("leading");
		SQL_KEYWORDS.add("like");
		SQL_KEYWORDS.add("lines");
		SQL_KEYWORDS.add("localtimestamp");
		SQL_KEYWORDS.add("longblob");
		SQL_KEYWORDS.add("low_priority");
		SQL_KEYWORDS.add("mediumint");
		SQL_KEYWORDS.add("minute_microsecond");
		SQL_KEYWORDS.add("modifies");
		SQL_KEYWORDS.add("no_write_to_binlog");
		SQL_KEYWORDS.add("on");
		SQL_KEYWORDS.add("optionally");
		SQL_KEYWORDS.add("out");
		SQL_KEYWORDS.add("precision");
		SQL_KEYWORDS.add("purge");
		SQL_KEYWORDS.add("read");
		SQL_KEYWORDS.add("references");
		SQL_KEYWORDS.add("rename");
		SQL_KEYWORDS.add("require");
		SQL_KEYWORDS.add("revoke");
		SQL_KEYWORDS.add("schema");
		SQL_KEYWORDS.add("select");
		SQL_KEYWORDS.add("set");
		SQL_KEYWORDS.add("spatial");
		SQL_KEYWORDS.add("sqlexception");
		SQL_KEYWORDS.add("sql_big_result");
		SQL_KEYWORDS.add("ssl");
		SQL_KEYWORDS.add("table");
		SQL_KEYWORDS.add("tinyblob");
		SQL_KEYWORDS.add("to");
		SQL_KEYWORDS.add("true");
		SQL_KEYWORDS.add("unique");
		SQL_KEYWORDS.add("update");
		SQL_KEYWORDS.add("using");
		SQL_KEYWORDS.add("utc_timestamp");
		SQL_KEYWORDS.add("varchar");
		SQL_KEYWORDS.add("when");
		SQL_KEYWORDS.add("with");
		SQL_KEYWORDS.add("xor");
		SQL_KEYWORDS.add("all");
		SQL_KEYWORDS.add("and");
		SQL_KEYWORDS.add("asensitive");
		SQL_KEYWORDS.add("bigint");
		SQL_KEYWORDS.add("both");
		SQL_KEYWORDS.add("cascade");
		SQL_KEYWORDS.add("char");
		SQL_KEYWORDS.add("collate");
		SQL_KEYWORDS.add("connection");
		SQL_KEYWORDS.add("convert");
		SQL_KEYWORDS.add("current_date");
		SQL_KEYWORDS.add("current_user");
		SQL_KEYWORDS.add("databases");
		SQL_KEYWORDS.add("day_minute");
		SQL_KEYWORDS.add("decimal");
		SQL_KEYWORDS.add("delayed");
		SQL_KEYWORDS.add("describe");
		SQL_KEYWORDS.add("distinctrow");
		SQL_KEYWORDS.add("drop");
		SQL_KEYWORDS.add("else");
		SQL_KEYWORDS.add("escaped");
		SQL_KEYWORDS.add("explain");
		SQL_KEYWORDS.add("float");
		SQL_KEYWORDS.add("for");
		SQL_KEYWORDS.add("from");
		SQL_KEYWORDS.add("grant");
		SQL_KEYWORDS.add("high_priority");
		SQL_KEYWORDS.add("hour_second");
		SQL_KEYWORDS.add("in");
		SQL_KEYWORDS.add("inner");
		SQL_KEYWORDS.add("insert");
		SQL_KEYWORDS.add("int2");
		SQL_KEYWORDS.add("int8");
		SQL_KEYWORDS.add("into");
		SQL_KEYWORDS.add("join");
		SQL_KEYWORDS.add("kill");
		SQL_KEYWORDS.add("leave");
		SQL_KEYWORDS.add("limit");
		SQL_KEYWORDS.add("load");
		SQL_KEYWORDS.add("lock");
		SQL_KEYWORDS.add("longtext");
		SQL_KEYWORDS.add("match");
		SQL_KEYWORDS.add("mediumtext");
		SQL_KEYWORDS.add("minute_second");
		SQL_KEYWORDS.add("natural");
		SQL_KEYWORDS.add("null");
		SQL_KEYWORDS.add("optimize");
		SQL_KEYWORDS.add("or");
		SQL_KEYWORDS.add("outer");
		SQL_KEYWORDS.add("primary");
		SQL_KEYWORDS.add("raid0");
		SQL_KEYWORDS.add("reads");
		SQL_KEYWORDS.add("regexp");
		SQL_KEYWORDS.add("repeat");
		SQL_KEYWORDS.add("restrict");
		SQL_KEYWORDS.add("right");
		SQL_KEYWORDS.add("schemas");
		SQL_KEYWORDS.add("sensitive");
		SQL_KEYWORDS.add("show");
		SQL_KEYWORDS.add("specific");
		SQL_KEYWORDS.add("sqlstate");
		SQL_KEYWORDS.add("sql_calc_found_rows");
		SQL_KEYWORDS.add("starting");
		SQL_KEYWORDS.add("terminated");
		SQL_KEYWORDS.add("tinyint");
		SQL_KEYWORDS.add("trailing");
		SQL_KEYWORDS.add("undo");
		SQL_KEYWORDS.add("unlock");
		SQL_KEYWORDS.add("usage");
		SQL_KEYWORDS.add("utc_date");
		SQL_KEYWORDS.add("values");
		SQL_KEYWORDS.add("varcharacter");
		SQL_KEYWORDS.add("where");
		SQL_KEYWORDS.add("write");
		SQL_KEYWORDS.add("year_month");
		SQL_KEYWORDS.add("alter");
		SQL_KEYWORDS.add("as");
		SQL_KEYWORDS.add("before");
		SQL_KEYWORDS.add("binary");
		SQL_KEYWORDS.add("by");
		SQL_KEYWORDS.add("case");
		SQL_KEYWORDS.add("character");
		SQL_KEYWORDS.add("column");
		SQL_KEYWORDS.add("constraint");
		SQL_KEYWORDS.add("create");
		SQL_KEYWORDS.add("current_time");
		SQL_KEYWORDS.add("cursor");
		SQL_KEYWORDS.add("day_hour");
		SQL_KEYWORDS.add("day_second");
		SQL_KEYWORDS.add("declare");
		SQL_KEYWORDS.add("delete");
		SQL_KEYWORDS.add("deterministic");
		SQL_KEYWORDS.add("div");
		SQL_KEYWORDS.add("dual");
		SQL_KEYWORDS.add("elseif");
		SQL_KEYWORDS.add("exists");
		SQL_KEYWORDS.add("FALSE");
		SQL_KEYWORDS.add("float4");
		SQL_KEYWORDS.add("force");
		SQL_KEYWORDS.add("fulltext");
		SQL_KEYWORDS.add("group");
		SQL_KEYWORDS.add("hour_microsecond");
		SQL_KEYWORDS.add("if");
		SQL_KEYWORDS.add("index");
		SQL_KEYWORDS.add("inout");
		SQL_KEYWORDS.add("int");
		SQL_KEYWORDS.add("int3");
		SQL_KEYWORDS.add("integer");
		SQL_KEYWORDS.add("is");
		SQL_KEYWORDS.add("key");
		SQL_KEYWORDS.add("label");
		SQL_KEYWORDS.add("left");
		SQL_KEYWORDS.add("linear");
		SQL_KEYWORDS.add("localtime");
		SQL_KEYWORDS.add("long");
		SQL_KEYWORDS.add("loop");
		SQL_KEYWORDS.add("mediumblob");
		SQL_KEYWORDS.add("middleint");
		SQL_KEYWORDS.add("mod");
		SQL_KEYWORDS.add("not");
		SQL_KEYWORDS.add("numeric");
		SQL_KEYWORDS.add("option");
		//SQL_KEYWORDS.add("order");
		SQL_KEYWORDS.add("outfile");
		SQL_KEYWORDS.add("procedure");
		SQL_KEYWORDS.add("range");
		SQL_KEYWORDS.add("real");
		SQL_KEYWORDS.add("release");
		SQL_KEYWORDS.add("replace");
		SQL_KEYWORDS.add("return");
		SQL_KEYWORDS.add("rlike");
		SQL_KEYWORDS.add("second_microsecond");
		SQL_KEYWORDS.add("separator");
		SQL_KEYWORDS.add("smallint");
		SQL_KEYWORDS.add("sql");
		SQL_KEYWORDS.add("sqlwarning");
		SQL_KEYWORDS.add("sql_small_result");
		SQL_KEYWORDS.add("straight_join");
		SQL_KEYWORDS.add("then");
		SQL_KEYWORDS.add("tinytext");
		SQL_KEYWORDS.add("trigger");
		SQL_KEYWORDS.add("union");
		SQL_KEYWORDS.add("unsigned");
		SQL_KEYWORDS.add("use");
		SQL_KEYWORDS.add("utc_time");
		SQL_KEYWORDS.add("varbinary");
		SQL_KEYWORDS.add("varying");
		SQL_KEYWORDS.add("while");
		SQL_KEYWORDS.add("x509");
		SQL_KEYWORDS.add("zerofill");
		SQL_KEYWORDS_COUNT = SQL_KEYWORDS.size();
		SQL_CHARS_COUNT = SQL_CHARS.size();
	}

	/**
	 * 验证字符串是否包含sql注入特殊字符串
	 * 
	 * @param paramVal
	 * @return
	 */
	public static boolean isSQLInject(String paramVal) {
		if (StrUtil.isEmpty(paramVal)) {
			return false;
		}
		int len = paramVal.length();
		// 检测SQL注入特殊符号
		for (int index = 0; index < SQL_CHARS_COUNT; index++) {
			String chars = SQL_CHARS.get(index);
			if (len < chars.length()) {
				continue;
			}
			if (paramVal.equals(chars) || paramVal.indexOf(chars) >= 0) {
				return true;
			}
		}
		paramVal = paramVal.toLowerCase();
		// 检测SQL注入关键字,该关键字前后有空格,即该关键字独立.
		for (int index = 0; index < SQL_KEYWORDS_COUNT; index++) {
			String keyword = SQL_KEYWORDS.get(index);
			if (len < keyword.length()) {
				continue;
			}
			if (paramVal.equals(keyword)) {
				return true;
			}
			if (paramVal.startsWith(keyword + ' ')) {
				return true;
			}
			if (paramVal.endsWith(' ' + keyword)) {
				return true;
			}
			if (-1 != paramVal.indexOf(' ' + keyword + ' ')) {
				return true;
			}
		}
		return false;
	}
}
