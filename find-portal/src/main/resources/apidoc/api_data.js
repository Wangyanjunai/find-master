define({ "api": [
  {
    "type": "put",
    "url": "http://8.135.36.45:8084/find/dynamic/{id}/share",
    "title": "分享动态内容接口",
    "version": "1.0.0",
    "group": "动态模块API",
    "name": "分享内容动态",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "dynamicInfoId",
            "description": "<p>动态内容id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "allowedValues": [
              "{\"0\"",
              "\"1\"",
              "\"2\"",
              "\"3\"",
              "\"4\"}"
            ],
            "optional": false,
            "field": "mode",
            "description": "<p>分享方式：0-&gt;微信好友，1-&gt;QQ好友，2-&gt;微信朋友圈，3-&gt;QQ空间，4-&gt;微信收藏</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://8.135.36.45:8084/find/dynamic/70/share?dynamicInfoId=86&mode=0",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "SHARED",
            "description": "<p>分享状态，OK-&gt;成功，ERROR-&gt;失败，说明：成功，分享数+1，失败，不处理</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"分享动态内容成功。\",\n\"data\": {\n\"SHARED\": \"OK\"\n}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "timestamp",
            "description": "<p>响应时间戳</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "status",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "error",
            "description": "<p>错误说明</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回说明</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "path",
            "description": "<p>路径</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应 接口未注册\n{\n\"timestamp\": 1611558682334,\n\"status\": 404,\n\"error\": \"Not Found\",\n\"message\": \"No message available\",\n\"path\": \"/find/dynamic/70/share1\"\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应！\",\n\"data\": null\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/DynamicController.java",
    "groupTitle": "动态模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/dynamic/{id}/share"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://8.135.36.45:8084/find/dynamic/{id}/delete",
    "title": "删除动态内容接口",
    "version": "1.0.0",
    "group": "动态模块API",
    "name": "删除动态内容",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "dynamicInfoId",
            "description": "<p>动态内容id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例01（是自己发布的动态内容， 删除成功）",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://8.135.36.45:8084/find/dynamic/70/delete?dynamicInfoId=85",
          "type": "json"
        },
        {
          "title": "请求示例02（非自己发布的动态内容， 删除失败）",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://8.135.36.45:8084/find/dynamic/70/delete?dynamicInfoId=86",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "DELETED",
            "description": "<p>删除状态，OK-&gt;成功，ERROR-&gt;失败</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例01（是自己发布的动态内容， 删除成功）",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"删除动态内容成功。\",\n\"data\": {\n\"DELETED\": \"OK\"\n}\n}",
          "type": "json"
        },
        {
          "title": "200 响应示例02（非自己发布的动态内容， 删除失败）",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"删除动态内容失败。\",\n\"data\": {\n\"DELETED\": \"ERROR\"\n}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "timestamp",
            "description": "<p>响应时间戳</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "status",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "error",
            "description": "<p>错误说明</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回说明</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "path",
            "description": "<p>路径</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应 接口未注册\n{\n\"timestamp\": 1611558682334,\n\"status\": 404,\n\"error\": \"Not Found\",\n\"message\": \"No message available\",\n\"path\": \"/find/dynamic/1/delete1\"\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应！\",\n\"data\": null\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/DynamicController.java",
    "groupTitle": "动态模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/dynamic/{id}/delete"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://8.135.36.45:8084/find/dynamic/{id}/release",
    "title": "发布动态内容接口",
    "version": "1.0.0",
    "group": "动态模块API",
    "name": "发布动态内容",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "imei",
            "description": "<p>设备串码</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "allowedValues": [
              "{\"0\"",
              "\"1\"}"
            ],
            "optional": false,
            "field": "attacheInfoDataType",
            "description": "<p>附件类型：0-&gt;图片；1-&gt;语音</p>"
          },
          {
            "group": "接口请求参数",
            "type": "file[]",
            "allowedValues": [
              "{1..4}"
            ],
            "optional": true,
            "field": "files",
            "description": "<p>附件数组，说明：图片文件不能超过4张包括4张，语音文件不能超过1个包括1个</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "model",
            "description": "<p>设备型号</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "sysName",
            "description": "<p>系统名称</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "sysCode",
            "description": "<p>系统版本</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "allowedValues": [
              "{\"2G\"",
              "\"3G\"",
              "\"4G\"",
              "\"5G\"",
              "\"WIFI\""
            ],
            "optional": true,
            "field": "networkMode",
            "description": "<p>上网方式</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "ip",
            "description": "<p>客户端IP</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "country",
            "description": "<p>定位（国家）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "province",
            "description": "<p>定位（省份）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "city",
            "description": "<p>定位（城市）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "allowedValues": [
              "{\"0\"",
              "\"1\"}"
            ],
            "optional": true,
            "field": "publicStatus",
            "description": "<p>是否公开定位，0-&gt;未公开；1-&gt;公开，默认0</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "content",
            "description": "<p>动态内容</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例01（发布图片有具体发布定位地址）",
          "content": "HTTP/1.1 OK 注：form表单提交，需要在请求头加：“Content-Type=multipart/form-data;charset=utf-8”\ncurl -v -X POST -H 'multipart/form-data;charset=utf-8' http://8.135.36.45:8084/find/dynamic/3/release\n-d '{\n\"imei\": \"895568564457954422\",\n\"attacheInfoDataType\": \"1\",\n\"files\": \"C:\\Users\\Administrator\\Pictures\\images\\01.jpg,\nC:\\Users\\Administrator\\Pictures\\images\\02.jpg,\nC:\\Users\\Administrator\\Pictures\\images\\03.jpg,\nC:\\Users\\Administrator\\Pictures\\images\\04.jpg\",\n\"model\": \"vivo x7 plus\",\n\"sysName\": \"Android\",\n\"sysCode\": \"9.0\",\n\"networkMode\": \"WIFI\",\n\"country\": \"中国\",\n\"province\": \"广东\",\n\"city\": \"广州\",\n\"publicStatus\": \"0\",\n\"content\": \"发布照片。\"\n}'",
          "type": "json"
        },
        {
          "title": "请求示例02（发布图片有客户端IP）",
          "content": "HTTP/1.1 OK 注：form表单提交，需要在请求头加：“Content-Type=multipart/form-data;charset=utf-8”\ncurl -v -X POST -H 'multipart/form-data;charset=utf-8' http://8.135.36.45:8084/find/dynamic/3/release\n-d '{\n\"imei\": \"895568564457954422\",\n\"attacheInfoDataType\": \"1\",\n\"files\": \"C:\\Users\\Administrator\\Pictures\\images\\01.jpg,\nC:\\Users\\Administrator\\Pictures\\images\\02.jpg,\nC:\\Users\\Administrator\\Pictures\\images\\03.jpg,\nC:\\Users\\Administrator\\Pictures\\images\\04.jpg\",\n\"model\": \"vivo x7 plus\",\n\"sysName\": \"Android\",\n\"sysCode\": \"9.0\",\n\"networkMode\": \"WIFI\",\n\"ip\": \"183.14.31.54\",\n\"publicStatus\": \"0\",\n\"content\": \"发布照片。\"\n}'",
          "type": "json"
        },
        {
          "title": "请求示例03（发布语音有客户端IP）",
          "content": "HTTP/1.1 OK 注：form表单提交，需要在请求头加：“Content-Type=multipart/form-data;charset=utf-8”\ncurl -v -X POST -H 'multipart/form-data;charset=utf-8' http://8.135.36.45:8084/find/dynamic/3/release\n-d '{\n\"imei\": \"895568564457954422\",\n\"attacheInfoDataType\": \"2\",\n\"files\": \"F:\\文件\\各种音乐\\(DJ)中文DJ\\7姨、高梦瑶、妖姬 - 威震八方.mp3\",\n\"model\": \"vivo x7 plus\",\n\"sysName\": \"Android\",\n\"sysCode\": \"9.0\",\n\"networkMode\": \"WIFI\",\n\"ip\": \"183.14.31.54\",\n\"publicStatus\": \"0\",\n\"content\": \"发布语音。\"\n}'",
          "type": "json"
        },
        {
          "title": "请求示例04（发布纯文字有客户端IP）",
          "content": "HTTP/1.1 OK 注：form表单提交，需要在请求头加：“Content-Type=multipart/form-data;charset=utf-8”\ncurl -v -X POST -H 'multipart/form-data;charset=utf-8' http://8.135.36.45:8084/find/dynamic/3/release\n-d '{\n\"imei\": \"895568564457954422\",\n\"attacheInfoDataType\": \"0\",\n\"model\": \"vivo x7 plus\",\n\"sysName\": \"Android\",\n\"sysCode\": \"9.0\",\n\"networkMode\": \"WIFI\",\n\"ip\": \"183.14.31.54\",\n\"publicStatus\": \"0\",\n\"content\": \"发布语音。\"\n}'",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "RELEASED",
            "description": "<p>发布状态</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例01（发布图片有具体发布定位地址）",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"发布动态内容成功。\",\n\"data\": {\n\"RELEASED\": \"OK\"\n}\n}",
          "type": "json"
        },
        {
          "title": "200响应示例02（发布图片有客户端IP）",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"发布动态内容成功。\",\n\"data\": {\n\"RELEASED\": \"OK\"\n}\n}",
          "type": "json"
        },
        {
          "title": "200响应示例03（发布语音有客户端IP）",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"发布动态内容成功。\",\n\"data\": {\n\"RELEASED\": \"OK\"\n}\n}",
          "type": "json"
        },
        {
          "title": "200响应示例04（发布纯文字有客户端IP）",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"发布动态内容成功。\",\n\"data\": {\n\"RELEASED\": \"OK\"\n}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "403": [
          {
            "group": "403",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "403",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "403",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "403错误",
          "content": "HTTP/1.1 403 403响应\n{\n\"status\": 403,\n\"code\": 199,\n\"msg\": \"未找到用户信息！\"\n}",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册！\"\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应！\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/DynamicController.java",
    "groupTitle": "动态模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/dynamic/{id}/release"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://8.135.36.45:8084/find/dynamic/{id}/updateLocation",
    "title": "更新动态地址定位接口",
    "version": "1.0.0",
    "group": "动态模块API",
    "name": "更新动态地址定位",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "ip",
            "description": "<p>客户端IP，说明：不能与定位（国家）、（省份）、（城市）同时为空，如果同时都不为空，以定位（国家）、（省份）、（城市）为准</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "country",
            "description": "<p>定位（国家）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "province",
            "description": "<p>定位（省份）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "city",
            "description": "<p>定位（城市）</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例01（有客户端IP）",
          "content": "HTTP/1.1 OK\ncurl -v -X POST -H 'application/json;charset=utf-8' http://8.135.36.45:8084/find/dynamic/1/updateLocation -d '{\"ip\":\"183.14.133.239\"}'",
          "type": "json"
        },
        {
          "title": "请求示例02（有定位（国家）、（省份）、（城市））",
          "content": "HTTP/1.1 OK\ncurl -v -X POST -H 'application/json;charset=utf-8' http://8.135.36.45:8084/find/dynamic/1/updateLocation -d '{\"country\": \"中国\", \"province\": \"广东\", \"city\": \"深圳\"}'",
          "type": "json"
        },
        {
          "title": "请求示例03（有客户端IP，定位（国家）、（省份）、（城市））",
          "content": "HTTP/1.1 OK\ncurl -v -X POST -H 'application/json;charset=utf-8' http://8.135.36.45:8084/find/dynamic/1/updateLocation\n-d '{\n\"ip\": \"183.14.133.239\",\n\"country\": \"中国\",\n\"province\": \"广东\",\n\"city\": \"深圳\"\n}'",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "UPDATE",
            "description": "<p>更新状态，OK-&gt;成功，ERROR-&gt;失败</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "boolean",
            "optional": true,
            "field": "CHANGED",
            "description": "<p>是否发生更改，true-&gt;是，false-&gt;否</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例01（有客户端IP）",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"更新用户发布动态定位成功。\",\n\"data\": {\n\"UPDATE\": \"OK\"\n}\n}",
          "type": "json"
        },
        {
          "title": "200 响应示例02（有有定位（国家）、（省份）、（城市））",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"更新用户发布动态定位成功。\",\n\"data\": {\n\"UPDATE\": \"OK\"\n}\n}",
          "type": "json"
        },
        {
          "title": "200响应示例03（有客户端IP，发布定位地址（国）、（省）、（市））",
          "content": "HTTP/1.1 200 OK\ncurl -v -X POST -H 'application/json;charset=utf-8' http://8.135.36.45:8084/find/dynamic/1/updateLocation",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "403错误 （客户端IP，定位（国家）、（省份）、（城市）都为空）",
          "content": "HTTP/1.1 400 400响应\n{\n\"status\": 400,\n\"code\": 500,\n\"msg\": \"检查失败，客户端IP，发布动态定位（国）、（省）、（市）不能同时不传或者为空。\",\n\"data\": null\n}",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应 接口未注册\n{\n\"timestamp\": 1611558682334,\n\"status\": 404,\n\"error\": \"Not Found\",\n\"message\": \"No message available\",\n\"path\": \"/find/dynamic/1/updateLocation1\"\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应！\",\n\"data\": null\n}",
          "type": "json"
        }
      ],
      "fields": {
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "timestamp",
            "description": "<p>响应时间戳</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "status",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "error",
            "description": "<p>错误说明</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回说明</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "path",
            "description": "<p>路径</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/DynamicController.java",
    "groupTitle": "动态模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/dynamic/{id}/updateLocation"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://8.135.36.45:8084/find/dynamic/{id}/checkLocation",
    "title": "检查定位地址是否更改接口",
    "version": "1.0.0",
    "group": "动态模块API",
    "name": "检查定位地址是否更改",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "ip",
            "description": "<p>客户端IP，不能与发布动态定位（国）、（省）、（市）同时不传或者为空，如果同时都传或者都不为空，以传的发布动态定位（国）、（省）、（市）为准</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "country",
            "description": "<p>发布动态定位（国）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "province",
            "description": "<p>发布动态定位（省）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "city",
            "description": "<p>发布动态定位（市）</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例01（有客户端IP）",
          "content": "HTTP/1.1 OK\ncurl -v -X POST -H 'application/json;charset=utf-8' http://8.135.36.45:8084/find/dynamic/1/checkLocation -d '{\"ip\":\"183.14.133.239\"}'",
          "type": "json"
        },
        {
          "title": "请求示例02（有发布定位地址（国）、省、市）",
          "content": "HTTP/1.1 OK\ncurl -v -X POST -H 'application/json;charset=utf-8' http://8.135.36.45:8084/find/dynamic/1/checkLocation -d '{\"country\": \"中国\", \"province\": \"广东\", \"city\": \"深圳\"}'",
          "type": "json"
        },
        {
          "title": "请求示例03（有客户端IP，发布定位地址（国）、（省）、（市））",
          "content": "HTTP/1.1 OK\ncurl -v -X POST -H 'application/json;charset=utf-8' http://8.135.36.45:8084/find/dynamic/1/checkLocation\n-d '{\n\"ip\": \"183.14.133.239\",\n\"country\": \"中国\",\n\"province\": \"广东\",\n\"city\": \"深圳\"\n}'",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "boolean",
            "optional": true,
            "field": "CHANGED",
            "description": "<p>是否发生更改，true-&gt;是，false-&gt;否</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例01（有客户端IP）",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"检查成功。\",\n\"data\": {\n\"CHANGED\": true\n}\n}",
          "type": "json"
        },
        {
          "title": "200 响应示例02（有发布定位地址（国）、（省）、（市））",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"检查成功。\",\n\"data\": {\n\"CHANGED\": true\n}\n}",
          "type": "json"
        },
        {
          "title": "200响应示例03（有客户端IP，发布定位地址（国）、（省）、（市））",
          "content": "HTTP/1.1 200 OK\ncurl -v -X POST -H 'application/json;charset=utf-8' http://8.135.36.45:8084/find/dynamic/1/checkLocation -d '{}'",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "403错误 （客户端IP，发布定位地址（国）、（省）、（市）都不传）",
          "content": "HTTP/1.1 400 400响应\n{\n\"status\": 400,\n\"code\": 500,\n\"msg\": \"检查失败，客户端IP，发布动态定位（国）、（省）、（市）不能同时不传或者为空。\",\n\"data\": null\n}",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应 接口未注册\n{\n\"timestamp\": 1611558682334,\n\"status\": 404,\n\"error\": \"Not Found\",\n\"message\": \"No message available\",\n\"path\": \"/find/v1/dynamic/1/checkLocation1\"\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应！\",\n\"data\": null\n}",
          "type": "json"
        }
      ],
      "fields": {
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "timestamp",
            "description": "<p>响应时间戳</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "status",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "error",
            "description": "<p>错误说明</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回说明</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "path",
            "description": "<p>路径</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/DynamicController.java",
    "groupTitle": "动态模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/dynamic/{id}/checkLocation"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://8.135.36.45:8084/find/dynamic/{id}/likes",
    "title": "点赞或取消点赞接口",
    "version": "1.0.0",
    "group": "动态模块API",
    "name": "点赞或取消点赞",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "dynamicInfoId",
            "description": "<p>动态内容id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "\"0\", \"1\"",
            "optional": false,
            "field": "type",
            "description": "<p>类型，0-&gt;取消，1-&gt;点赞</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例01（取消，点赞记录不存在）",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://8.135.36.45:8084/find/dynamic/70/likes?dynamicInfoId=86&type=0",
          "type": "json"
        },
        {
          "title": "请求示例02（取消点赞，点赞记录存在）",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://8.135.36.45:8084/find/dynamic/70/likes?dynamicInfoId=86&type=0",
          "type": "json"
        },
        {
          "title": "请求示例03（点赞，点赞记录不存在）",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://8.135.36.45:8084/find/dynamic/70/likes?dynamicInfoId=86&type=1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "LIKED",
            "description": "<p>点赞或取消点赞状态，OK-&gt;成功，ERROR-&gt;失败</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例01（取消，点赞记录不存在）",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"取消点赞，点赞记录信息不存在。\",\n\"data\": {\n\"LIKED\": \"ERROR\"\n}\n}",
          "type": "json"
        },
        {
          "title": "200 响应示例02（取消，点赞记录存在）",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"取消点赞，取消成功。\",\n\"data\": {\n\"LIKED\": \"OK\"\n}\n}",
          "type": "json"
        },
        {
          "title": "200 响应示例03（点赞，点赞记录不存在）",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"创建点赞，点赞成功。\",\n\"data\": {\n\"LIKED\": \"OK\"\n}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "timestamp",
            "description": "<p>响应时间戳</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "status",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "error",
            "description": "<p>错误说明</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回说明</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "path",
            "description": "<p>路径</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应 接口未注册\n{\n\"timestamp\": 1611558682334,\n\"status\": 404,\n\"error\": \"Not Found\",\n\"message\": \"No message available\",\n\"path\": \"/find/dynamic/70/likes1\"\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应！\",\n\"data\": null\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/DynamicController.java",
    "groupTitle": "动态模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/dynamic/{id}/likes"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://8.135.36.45:8084/find/dynamic/{id}/application",
    "title": "申请加微信接口",
    "version": "1.0.0",
    "group": "动态模块API",
    "name": "申请加微信",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>申请者用户id，说明：普通用户每天只允许申请最多5次添加微信，VIP用户申请加微信次数没有限制</p>"
          },
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "dynamicInfoId",
            "description": "<p>动态内容id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "..255",
            "optional": true,
            "field": "message",
            "description": "<p>发送的消息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例01（第1次申请加微信）",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://8.135.36.45:8084/find/dynamic/70/application?dynamicInfoId=86&message=需要加您的微信，请发送微信号码过来",
          "type": "json"
        },
        {
          "title": "请求示例02（第6次申请加微信）",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://8.135.36.45:8084/find/dynamic/70/application?dynamicInfoId=86&message=需要加您的微信16",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "APPLICATION",
            "description": "<p>申请加微信状态，OK-&gt;成功，ERROR-&gt;失败</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例01（第1次申请加微信）",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"申请加微信成功。\",\n\"data\": {\n\"APPLICATION\": \"OK\"\n}\n}",
          "type": "json"
        },
        {
          "title": "200 响应示例02（第6次申请加微信）",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"申请加微信出错，当天申请加微信次数超限。\",\n\"data\": {\n\"APPLICATION\": \"ERROR\"\n}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "timestamp",
            "description": "<p>响应时间戳</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "status",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "error",
            "description": "<p>错误说明</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回说明</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "path",
            "description": "<p>路径</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应 接口未注册\n{\n\"timestamp\": 1611558682334,\n\"status\": 404,\n\"error\": \"Not Found\",\n\"message\": \"No message available\",\n\"path\": \"find/dynamic/70/application1\"\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应！\",\n\"data\": null\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/DynamicController.java",
    "groupTitle": "动态模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/dynamic/{id}/application"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://8.135.36.45:8084/find/dynamic/{id}/screen",
    "title": "筛选动态内容列表接口",
    "version": "1.0.0",
    "group": "动态模块API",
    "name": "筛选动态内容列表",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "int",
            "optional": true,
            "field": "pageNum",
            "description": "<p>当前页数，默认：第1页</p>"
          },
          {
            "group": "接口请求参数",
            "type": "int",
            "optional": true,
            "field": "pageSize",
            "description": "<p>每页条数，默认：每页20条</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "allowedValues": [
              "{\"0\"",
              "\"1\"",
              "\"2\"}"
            ],
            "optional": true,
            "field": "gender",
            "description": "<p>性别，0-&gt;女生，1-&gt;男生，2-&gt;全部，默认：如果用户注册选择是0-&gt;男生，则筛选值：1-&gt;女生，反之，如果用户注册选择是1-&gt;女生，则筛选值为：0-&gt;男生</p>"
          },
          {
            "group": "接口请求参数",
            "type": "int",
            "optional": true,
            "field": "minAge",
            "description": "<p>年龄范围（最小值），默认：16</p>"
          },
          {
            "group": "接口请求参数",
            "type": "int",
            "optional": true,
            "field": "maxAge",
            "description": "<p>年龄范围（最大值），默认：35</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string[]",
            "allowedValues": [
              "{\"水瓶座\"",
              "\"双鱼座\"",
              "\"白羊座\"",
              "\"金牛座\"",
              "\"双子座\"",
              "\"巨蟹座\"",
              "\"狮子座\"",
              "\"处女座\"",
              "\"天秤座\"",
              "\"天蝎座\"",
              "\"射手座\"",
              "\"摩羯座\"}"
            ],
            "optional": true,
            "field": "constellation",
            "description": "<p>星座列表，默认：null，不限-&gt;不传此参数，此参数为：null，全部-&gt;不传此参数，此参数为：null</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "allowedValues": [
              "{\"0\"",
              "\"1\"",
              "\"2\"}"
            ],
            "optional": true,
            "field": "dataType",
            "description": "<p>附件类型，默认：0-&gt;全部，1-&gt;图片或者图片+文字，2-&gt;语音或者语音+文字</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string[]",
            "optional": true,
            "field": "provinceList",
            "description": "<p>省份列表，例如：广东省, 四川省</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string[]",
            "optional": true,
            "field": "cityList",
            "description": "<p>城市列表，例如：深圳市, 广州市, 成都市, 攀枝花市</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例01",
          "content": "HTTP/1.1 OK\ncurl -v -X GET http://8.135.36.45:8084/find/dynamic/71/screen?pageNum=1&pageSize=20&gender=0&minAge=16&maxAge=39&constellation=巨蟹座,水瓶座&dataType=0&provinceList=广东省,广西省,湖南省&cityList=深圳市,广州市,南宁市,长沙市",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "int",
            "optional": true,
            "field": "totalPage",
            "description": "<p>总页数</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "list",
            "description": "<p>动态内容数据列表</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "userId",
            "description": "<p>用户id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "headUrl",
            "description": "<p>头像图片地址</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "nickname",
            "description": "<p>昵称</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "publishTime",
            "description": "<p>发布时间</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "dynamicInfoId",
            "description": "<p>动态内容id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "content",
            "description": "<p>动态内容</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "address",
            "description": "<p>定位地址，如果发布动态时，公开定位，则会返回这条动态发布时的定位，否则不返回</p>"
          },
          {
            "group": "200",
            "type": "int",
            "optional": true,
            "field": "likes",
            "description": "<p>点赞数</p>"
          },
          {
            "group": "200",
            "type": "boolean",
            "optional": true,
            "field": "likeStatus",
            "description": "<p>点赞状态，true-&gt;已点赞，false-&gt;未点赞</p>"
          },
          {
            "group": "200",
            "type": "int",
            "optional": true,
            "field": "applications",
            "description": "<p>申请加微信数</p>"
          },
          {
            "group": "200",
            "type": "boolean",
            "optional": true,
            "field": "applicationStatus",
            "description": "<p>申请加微信状态，true-&gt;已申请，false-&gt;未申请</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "dataTye",
            "description": "<p>附件文件类型，0-&gt;图片，1-&gt;语音</p>"
          },
          {
            "group": "200",
            "type": "list",
            "optional": true,
            "field": "attacheFileUrlList",
            "description": "<p>附件文件地址列表</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例01",
          "content": "HTTP/1.1 200 OK\n{\n\"status\":200,\n\"code\":0,\n\"msg\":\"获取觅鹿界面发布的动态内容信息列表成功\",\n\"data\":{\n\"totalPage\":1,\n\"list\":[\n{\n\"userId\":71,\n\"headUrl\":\"http://8.135.36.45:8000/find/img/head/71/3cc0c052-e6ab-4b9a-b904-b4a577bd3413.jpg\",\n\"nickname\":\"杨贵妃\",\n\"publishTime\":\"2021-02-01 14:35:23\",\n\"dynamicInfoId\":86,\n\"content\":\"刚刚注册，请多关照小妹子！！\",\n\"address\":\"广西省南宁市\",\n\"likes\":0,\n\"likeStatus\":false,\n\"applications\":5,\n\"applicationStatus\":true,\n\"dataType\":\"1\",\n\"attacheFileUrlList\":[\n\"http://8.135.36.45:8000/find/res/images/71/20210201/1612161322850/3cc0c052-e6ab-4b9a-b904-b4a577bd3413.jpg\"\n]\n}\n]\n}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "timestamp",
            "description": "<p>响应时间戳</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "status",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "error",
            "description": "<p>错误说明</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回说明</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "path",
            "description": "<p>路径</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应 接口未注册\n{\n\"timestamp\": 1611558682334,\n\"status\": 404,\n\"error\": \"Not Found\",\n\"message\": \"No message available\",\n\"path\": \"/find/dynamic/70/screen1\"\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应！\",\n\"data\": null\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/DynamicController.java",
    "groupTitle": "动态模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/dynamic/{id}/screen"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://8.135.36.45:8084/find/dynamic/{id}/mylist",
    "title": "获取自己的动态内容列表接口",
    "version": "1.0.0",
    "group": "动态模块API",
    "name": "获取自己的动态内容列表",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "int",
            "optional": true,
            "field": "pageNum",
            "description": "<p>当前页数，默认：第1页</p>"
          },
          {
            "group": "接口请求参数",
            "type": "int",
            "optional": true,
            "field": "pageSize",
            "description": "<p>每页条数，默认：每页20条</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://8.135.36.45:8084/find/dynamic/71/mylist?pageNum=1&pageSize=20",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "int",
            "optional": true,
            "field": "totalPage",
            "description": "<p>总页数</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "list",
            "description": "<p>动态内容数据列表</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "userId",
            "description": "<p>用户id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "headUrl",
            "description": "<p>头像图片地址</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "nickname",
            "description": "<p>昵称</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "publishTime",
            "description": "<p>发布时间</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "dynamicInfoId",
            "description": "<p>动态内容id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "content",
            "description": "<p>动态内容</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "address",
            "description": "<p>定位地址，如果发布动态时，公开定位，则会返回这条动态发布时的定位，否则不返回</p>"
          },
          {
            "group": "200",
            "type": "int",
            "optional": true,
            "field": "likes",
            "description": "<p>点赞数</p>"
          },
          {
            "group": "200",
            "type": "boolean",
            "optional": true,
            "field": "likeStatus",
            "description": "<p>点赞状态，true-&gt;已点赞，false-&gt;未点赞</p>"
          },
          {
            "group": "200",
            "type": "int",
            "optional": true,
            "field": "applications",
            "description": "<p>申请加微信数</p>"
          },
          {
            "group": "200",
            "type": "boolean",
            "optional": true,
            "field": "applicationStatus",
            "description": "<p>申请加微信状态，true-&gt;已申请，false-&gt;未申请</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "dataTye",
            "description": "<p>附件文件类型，0-&gt;图片，1-&gt;语音</p>"
          },
          {
            "group": "200",
            "type": "list",
            "optional": true,
            "field": "attacheFileUrlList",
            "description": "<p>附件文件地址列表</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n{\n\"status\":200,\n\"code\":0,\n\"msg\":\"分页获取用户自己发布的所有动态内容列表成功。\",\n\"data\":{\n\"totalPage\":1,\n\"list\":[\n{\n\"userId\":71,\n\"headUrl\":\"http://8.135.36.45:8000/find/img/head/71/3cc0c052-e6ab-4b9a-b904-b4a577bd3413.jpg\",\n\"nickname\":\"杨贵妃\",\n\"publishTime\":\"2021-02-01 14:35:23\",\n\"dynamicInfoId\":86,\n\"content\":\"刚刚注册，请多关照小妹子！！\",\n\"address\":\"广西省南宁市\",\n\"likes\":0,\n\"likeStatus\":false,\n\"applications\":5,\n\"applicationStatus\":false,\n\"dataType\":\"0\",\n\"attacheFileUrlList\":[\n\"http://8.135.36.45:8000/find/res/images/71/20210201/1612161322850/3cc0c052-e6ab-4b9a-b904-b4a577bd3413.jpg\"\n]\n}\n]\n}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "timestamp",
            "description": "<p>响应时间戳</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "status",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "error",
            "description": "<p>错误说明</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回说明</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "path",
            "description": "<p>路径</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应 接口未注册\n{\n\"timestamp\": 1611558682334,\n\"status\": 404,\n\"error\": \"Not Found\",\n\"message\": \"No message available\",\n\"path\": \"find/dynamic/71/mylist1\"\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应！\",\n\"data\": null\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/DynamicController.java",
    "groupTitle": "动态模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/dynamic/{id}/mylist"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://8.135.36.45:8084/find/dynamic/{id}/list",
    "title": "觅鹿主界面动态内容列表接口",
    "version": "1.0.0",
    "group": "动态模块API",
    "name": "觅鹿主界面动态内容列表",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "int",
            "optional": true,
            "field": "pageNum",
            "description": "<p>当前页数，默认：第1页</p>"
          },
          {
            "group": "接口请求参数",
            "type": "int",
            "optional": true,
            "field": "pageSize",
            "description": "<p>每页条数，默认：每页20条</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例01",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://8.135.36.45:8084/find/dynamic/73/list?pageNum=1&pageSize=20",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "int",
            "optional": true,
            "field": "totalPage",
            "description": "<p>总页数</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "list",
            "description": "<p>动态内容数据列表</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "userId",
            "description": "<p>用户id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "headUrl",
            "description": "<p>头像图片地址</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "nickname",
            "description": "<p>昵称</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "publishTime",
            "description": "<p>发布时间</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "dynamicInfoId",
            "description": "<p>动态内容id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "content",
            "description": "<p>动态内容</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "address",
            "description": "<p>定位地址，如果发布动态时，公开定位，则会返回这条动态发布时的定位，否则不返回</p>"
          },
          {
            "group": "200",
            "type": "int",
            "optional": true,
            "field": "likes",
            "description": "<p>点赞数</p>"
          },
          {
            "group": "200",
            "type": "boolean",
            "optional": true,
            "field": "likeStatus",
            "description": "<p>点赞状态，true-&gt;已点赞，false-&gt;未点赞</p>"
          },
          {
            "group": "200",
            "type": "int",
            "optional": true,
            "field": "applications",
            "description": "<p>申请加微信数</p>"
          },
          {
            "group": "200",
            "type": "boolean",
            "optional": true,
            "field": "applicationStatus",
            "description": "<p>申请加微信状态，true-&gt;已申请，false-&gt;未申请</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "dataTye",
            "description": "<p>附件文件类型，0-&gt;图片，1-&gt;语音</p>"
          },
          {
            "group": "200",
            "type": "string[]",
            "optional": true,
            "field": "attacheFileUrlList",
            "description": "<p>附件文件地址列表</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例01",
          "content": "HTTP/1.1 200 OK\n{\n\"status\":200,\n\"code\":0,\n\"msg\":\"获取觅鹿界面发布的动态内容信息列表成功\",\n\"data\":{\n\"totalPage\":1,\n\"list\":[\n{\n\"userId\":71,\n\"headUrl\":\"http://8.135.36.45:8000/find/img/head/71/3cc0c052-e6ab-4b9a-b904-b4a577bd3413.jpg\",\n\"nickname\":\"杨贵妃\",\n\"publishTime\":\"2021-02-01 14:35:23\",\n\"dynamicInfoId\":86,\n\"content\":\"刚刚注册，请多关照小妹子！！\",\n\"address\":\"广西省南宁市\",\n\"likes\":0,\n\"likeStatus\":false,\n\"applications\":5,\n\"applicationStatus\":true,\n\"dataType\":\"1\",\n\"attacheFileUrlList\":[\n\"http://8.135.36.45:8000/find/res/images/71/20210201/1612161322850/3cc0c052-e6ab-4b9a-b904-b4a577bd3413.jpg\"\n]\n}\n]\n}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "timestamp",
            "description": "<p>响应时间戳</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "status",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "error",
            "description": "<p>错误说明</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回说明</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "path",
            "description": "<p>路径</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应 接口未注册\n{\n\"timestamp\": 1611558682334,\n\"status\": 404,\n\"error\": \"Not Found\",\n\"message\": \"No message available\",\n\"path\": \"/find/dynamic/70/list\"\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应！\",\n\"data\": null\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/DynamicController.java",
    "groupTitle": "动态模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/dynamic/{id}/list"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://8.135.36.45:8084/find/message/{id}/updateAll",
    "title": "全部消息已读接口",
    "version": "1.0.0",
    "group": "消息模块API",
    "name": "全部消息已读",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>消息接收者用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT \"http://8.135.36.45:8084/find/message/60/updateAll\" -H \"accept: application/json\"",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>标记已读状态数据</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "UPDATE",
            "description": "<p>OK-&gt;标记已读成功，ERROR-&gt;标记已读失败</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"标记已读成功。\",\n\"data\": {\n\"UPDATE\": \"OK\"\n}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "403": [
          {
            "group": "403",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "403",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "403",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "403错误",
          "content": "HTTP/1.1 403 403响应\n{\n\"status\": 403,\n\"code\": 199,\n\"msg\": \"未找到用户信息！\"\n}",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册！\"\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应！\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/MessageController.java",
    "groupTitle": "消息模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/message/{id}/updateAll"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://8.135.36.45:8084/find/message/{id1}/{id2}/messages",
    "title": "分页获取消息历史记录列表接口",
    "version": "1.0.0",
    "group": "消息模块API",
    "name": "分页获取消息历史记录列表",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id1",
            "description": "<p>消息发送者用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id2",
            "description": "<p>消息接收者用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "int",
            "optional": true,
            "field": "pageNum",
            "description": "<p>当前页码，默认：1</p>"
          },
          {
            "group": "接口请求参数",
            "type": "int",
            "optional": true,
            "field": "pageSize",
            "description": "<p>每页数量，默认：20</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例",
          "content": "HTTP/1.1 OK\ncurl -v -X GET \"http://8.135.36.45:8084/find/message/138/139/messages?pageNum=1&pageSize=20\" -H \"accept: application/json\"",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>消息历史记录数据</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "totalCount",
            "description": "<p>消息记录总条数</p>"
          },
          {
            "group": "200",
            "type": "int",
            "optional": true,
            "field": "totalPage",
            "description": "<p>消息记录总页数</p>"
          },
          {
            "group": "200",
            "type": "object[]",
            "optional": true,
            "field": "list",
            "description": "<p>消息记录数据列表</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "messageId",
            "description": "<p>消息记录id</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "sendUserId",
            "description": "<p>消息发送者用户id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "sendUserHead",
            "description": "<p>消息发送者用户头像</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "sendUserNickname",
            "description": "<p>消息发送者用户昵称</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "sendDateTime",
            "description": "<p>消息发送时间</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "content",
            "description": "<p>消息内容</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"返回数据成功。\",\n\"data\": {\n\"totalCount\": 4,\n\"totalPage\": 1,\n\"list\": [\n{\n\"messageId\": 8,\n\"sendUserId\": 139,\n\"sendUserHead\": \"http://8.135.36.45:8000/find/img/head/139/e2a31a97-c64d-467e-9df8-b0ed5b1cc09b.jpeg\",\n\"sendUserNickname\": \"9527\",\n\"sendDateTime\": \"2021年04月06日 11:50:30\",\n\"content\": \"申请加您的微信，麻烦通过一下，谢谢！\"\n},\n{\n\"messageId\": 9,\n\"sendUserId\": 138,\n\"sendUserHead\": \"http://8.135.36.45:8000/find/img/head/138/644406af-ebc4-4c85-b793-33e6f563d847.jpg\",\n\"sendUserNickname\": \"阿珂\",\n\"sendDateTime\": \"2021年04月06日 11:52:00\",\n\"content\": \"我同意。好的，我的微信号是：wx123123212\"\n},\n{\n\"messageId\": 18,\n\"sendUserId\": 139,\n\"sendUserHead\": \"http://8.135.36.45:8000/find/img/head/139/e2a31a97-c64d-467e-9df8-b0ed5b1cc09b.jpeg\",\n\"sendUserNickname\": \"9527\",\n\"sendDateTime\": \"2021年04月06日 15:24:43\",\n\"content\": \"添加微信聊聊\"\n},\n{\n\"messageId\": 32,\n\"sendUserId\": 138,\n\"sendUserHead\": \"http://8.135.36.45:8000/find/img/head/138/644406af-ebc4-4c85-b793-33e6f563d847.jpg\",\n\"sendUserNickname\": \"阿珂\",\n\"sendDateTime\": \"2021年04月06日 18:16:09\",\n\"content\": \"好啊\"\n}\n]\n}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "403": [
          {
            "group": "403",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "403",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "403",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "403错误",
          "content": "HTTP/1.1 403 403响应\n{\n\"status\": 403,\n\"code\": 199,\n\"msg\": \"未找到用户信息！\"\n}",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册！\"\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应！\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/MessageController.java",
    "groupTitle": "消息模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/message/{id1}/{id2}/messages"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://8.135.36.45:8084/find/message/{id}/all",
    "title": "分页获取消息界面点赞和申请加微信消息列表接口",
    "version": "1.0.0",
    "group": "消息模块API",
    "name": "分页获取消息界面点赞和申请加微信消息列表",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>接收者用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "int",
            "optional": true,
            "field": "pageNum",
            "description": "<p>当前页码，默认：1</p>"
          },
          {
            "group": "接口请求参数",
            "type": "int",
            "optional": true,
            "field": "pageSize",
            "description": "<p>每页数量，默认：20</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例",
          "content": "HTTP/1.1 OK\ncurl -v -X GET \"http://8.135.36.45:8084/find/message/29/all?pageNum=1&pageSize=20\" -H \"accept: application/json\"",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>消息数据</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "likes",
            "description": "<p>最新点赞消息</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "content1",
            "description": "<p>最新一条未读点赞消息内容</p>"
          },
          {
            "group": "200",
            "type": "int",
            "optional": true,
            "field": "count1",
            "description": "<p>未读点赞消息数量</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "totalCount",
            "description": "<p>申请加微信消息总条数</p>"
          },
          {
            "group": "200",
            "type": "int",
            "optional": true,
            "field": "totalPage",
            "description": "<p>申请加微信消息总页数</p>"
          },
          {
            "group": "200",
            "type": "object[]",
            "optional": true,
            "field": "list",
            "description": "<p>申请加微信消息数据</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "messageId",
            "description": "<p>申请加微信消息记录id</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "userId",
            "description": "<p>申请加微信发送者用户id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "head",
            "description": "<p>申请加微信发送者用户头像</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "nickname",
            "description": "<p>申请加微信发送者用户昵称</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "content2",
            "description": "<p>申请加微信发送消息内容</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "count2",
            "description": "<p>申请加微信未读消息数量</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n{\n\"status\":200,\n\"code\":0,\n\"msg\":\"返回数据成功\",\n\"data\":{\n\"likes\":{\n\"content1\":\"阿萌赞了你的动态差点就掉下去了！\",\n\"count1\":5\n},\n\"totalCount\":5,\n\"totalPage\":1,\n\"list\":[\n{\n\"messageId\": 7,\n\"userId\":60,\n\"head\":\"http://8.135.36.45:8000/find/img/head/60/01.png\",\n\"nickname\":\"尘埃\",\n\"content2\":\"需要加您的微信?\",\n\"count2\":5\n},\n{\n\"messageId\": 2,\n\"userId\":62,\n\"head\":\"http://8.135.36.45:8000/find/img/head/62/02.png\",\n\"nickname\":\"蓝梧桐\",\n\"content2\":\"需要加您的微信?\",\n\"count2\":5\n},\n{\n\"messageId\": 3,\n\"userId\":61,\n\"head\":\"http://8.135.36.45:8000/find/img/head/61/01.png\",\n\"nickname\":\"长安\",\n\"content2\":\"需要加您的微信?\",\n\"count2\":6\n},\n{\n\"messageId\": 4,\n\"userId\":71,\n\"head\":\"http://8.135.36.45:8000/find/img/head/71/07.png\",\n\"nickname\":\"弦雨晴\",\n\"content2\":\"需要加您的微信?\",\n\"count2\":6\n},\n{\n\"messageId\": 5,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"nickname\":\"阿萌\",\n\"content2\":\"需要加您的微信?\",\n\"count2\":1\n}\n]\n}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "403": [
          {
            "group": "403",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "403",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "403",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "403错误",
          "content": "HTTP/1.1 403 403响应\n{\n\"status\": 403,\n\"code\": 199,\n\"msg\": \"未找到用户信息！\"\n}",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册！\"\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应！\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/MessageController.java",
    "groupTitle": "消息模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/message/{id}/all"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://8.135.36.45:8084/find/message/{id}/likes",
    "title": "分页获取点赞消息列表接口",
    "version": "1.0.0",
    "group": "消息模块API",
    "name": "分页获取点赞消息列表",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>消息接收者用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "int",
            "optional": true,
            "field": "pageNum",
            "description": "<p>当前页码，默认：1</p>"
          },
          {
            "group": "接口请求参数",
            "type": "int",
            "optional": true,
            "field": "pageSize",
            "description": "<p>每页数量，默认：20</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例",
          "content": "HTTP/1.1 OK\ncurl -v -X GET \"http://8.135.36.45:8084/find/message/29/likes?pageNum=1&pageSize=20\" -H \"accept: application/json\"",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>消息数据</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "totalCount",
            "description": "<p>点赞消息总条数</p>"
          },
          {
            "group": "200",
            "type": "int",
            "optional": true,
            "field": "totalPage",
            "description": "<p>点赞消息总页数</p>"
          },
          {
            "group": "200",
            "type": "object[]",
            "optional": true,
            "field": "list",
            "description": "<p>点赞消息数据</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "userId",
            "description": "<p>点赞者用户id</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "messageId",
            "description": "<p>点赞消息记录id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "head",
            "description": "<p>点赞者用户头像</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "content",
            "description": "<p>点赞者发送消息内容</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "attacheType",
            "description": "<p>点赞的动态内容类型，0-&gt;图片，1-&gt;语音</p>"
          },
          {
            "group": "200",
            "type": "string[]",
            "optional": true,
            "field": "filenameList",
            "description": "<p>点赞的动态文件名称列表</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n{\n\"status\":200,\n\"code\":0,\n\"msg\":\"返回数据成功\",\n\"data\":{\n\"totalCount\":30,\n\"totalPage\":2,\n\"list\":[\n{\n\"messageId\":90,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"content\":\"阿萌赞了你的动态差点就掉下去了！\",\n\"attacheType\":\"0\",\n\"filenameList\":[\n\"http://8.135.36.45:8000/find/res/images/28/20200611/03.png\"\n]\n},\n{\n\"messageId\":89,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"content\":\"阿萌赞了你的动态差点就掉下去了！\",\n\"attacheType\":\"0\",\n\"filenameList\":[\n\"http://8.135.36.45:8000/find/res/images/29/20200427/014.png\"\n]\n},\n{\n\"messageId\":88,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"content\":\"阿萌赞了你的动态差点就掉下去了！\",\n\"attacheType\":\"0\",\n\"filenameList\":[\n\"http://8.135.36.45:8000/find/res/images/29/20200502/07.png\",\n\"http://8.135.36.45:8000/find/res/images/29/20200502/09.png\"\n]\n},\n{\n\"messageId\":87,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"content\":\"阿萌赞了你的动态差点就掉下去了！\",\n\"attacheType\":\"0\",\n\"filenameList\":[\n\"http://8.135.36.45:8000/find/res/images/29/20200503/03.png\",\n\"http://8.135.36.45:8000/find/res/images/29/20200503/05.png\",\n\"http://8.135.36.45:8000/find/res/images/29/20200503/08.png\"\n]\n},\n{\n\"messageId\":86,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"content\":\"阿萌赞了你的动态差点就掉下去了！\",\n\"attacheType\":\"0\",\n\"filenameList\":[\n\"http://8.135.36.45:8000/find/res/images/29/20200505/12.png\",\n\"http://8.135.36.45:8000/find/res/images/29/20200505/13.png\",\n\"http://8.135.36.45:8000/find/res/images/29/20200505/15.png\"\n]\n},\n{\n\"messageId\":85,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"content\":\"阿萌赞了你的动态差点就掉下去了！\",\n\"attacheType\":\"0\",\n\"filenameList\":[\n\"http://8.135.36.45:8000/find/res/images/29/20200507/04.png\"\n]\n},\n{\n\"messageId\":84,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"content\":\"阿萌赞了你的动态这组我比较喜欢\",\n\"attacheType\":\"0\",\n\"filenameList\":[\n\"http://8.135.36.45:8000/find/res/images/28/20200611/03.png\"\n]\n},\n{\n\"messageId\":83,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"content\":\"阿萌赞了你的动态这组我比较喜欢\",\n\"attacheType\":\"0\",\n\"filenameList\":[\n\"http://8.135.36.45:8000/find/res/images/29/20200427/014.png\"\n]\n},\n{\n\"messageId\":82,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"content\":\"阿萌赞了你的动态这组我比较喜欢\",\n\"attacheType\":\"0\",\n\"filenameList\":[\n\"http://8.135.36.45:8000/find/res/images/29/20200502/07.png\",\n\"http://8.135.36.45:8000/find/res/images/29/20200502/09.png\"\n]\n},\n{\n\"messageId\":81,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"content\":\"阿萌赞了你的动态这组我比较喜欢\",\n\"attacheType\":\"0\",\n\"filenameList\":[\n\"http://8.135.36.45:8000/find/res/images/29/20200503/03.png\",\n\"http://8.135.36.45:8000/find/res/images/29/20200503/05.png\",\n\"http://8.135.36.45:8000/find/res/images/29/20200503/08.png\"\n]\n},\n{\n\"messageId\":80,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"content\":\"阿萌赞了你的动态这组我比较喜欢\",\n\"attacheType\":\"0\",\n\"filenameList\":[\n\"http://8.135.36.45:8000/find/res/images/29/20200505/12.png\",\n\"http://8.135.36.45:8000/find/res/images/29/20200505/13.png\",\n\"http://8.135.36.45:8000/find/res/images/29/20200505/15.png\"\n]\n},\n{\n\"messageId\":79,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"content\":\"阿萌赞了你的动态这组我比较喜欢\",\n\"attacheType\":\"0\",\n\"filenameList\":[\n\"http://8.135.36.45:8000/find/res/images/29/20200507/04.png\"\n]\n},\n{\n\"messageId\":78,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"content\":\"阿萌赞了你的动态51出门熏人\",\n\"attacheType\":\"0\",\n\"filenameList\":[\n\"http://8.135.36.45:8000/find/res/images/28/20200611/03.png\"\n]\n},\n{\n\"messageId\":77,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"content\":\"阿萌赞了你的动态51出门熏人\",\n\"attacheType\":\"0\",\n\"filenameList\":[\n\"http://8.135.36.45:8000/find/res/images/29/20200427/014.png\"\n]\n},\n{\n\"messageId\":76,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"content\":\"阿萌赞了你的动态51出门熏人\",\n\"attacheType\":\"0\",\n\"filenameList\":[\n\"http://8.135.36.45:8000/find/res/images/29/20200502/07.png\",\n\"http://8.135.36.45:8000/find/res/images/29/20200502/09.png\"\n]\n},\n{\n\"messageId\":75,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"content\":\"阿萌赞了你的动态51出门熏人\",\n\"attacheType\":\"0\",\n\"filenameList\":[\n\"http://8.135.36.45:8000/find/res/images/29/20200503/03.png\",\n\"http://8.135.36.45:8000/find/res/images/29/20200503/05.png\",\n\"http://8.135.36.45:8000/find/res/images/29/20200503/08.png\"\n]\n},\n{\n\"messageId\":74,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"content\":\"阿萌赞了你的动态51出门熏人\",\n\"attacheType\":\"0\",\n\"filenameList\":[\n\"http://8.135.36.45:8000/find/res/images/29/20200505/12.png\",\n\"http://8.135.36.45:8000/find/res/images/29/20200505/13.png\",\n\"http://8.135.36.45:8000/find/res/images/29/20200505/15.png\"\n]\n},\n{\n\"messageId\":73,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"content\":\"阿萌赞了你的动态51出门熏人\",\n\"attacheType\":\"0\",\n\"filenameList\":[\n\"http://8.135.36.45:8000/find/res/images/29/20200507/04.png\"\n]\n},\n{\n\"messageId\":72,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"content\":\"阿萌赞了你的动态摩天轮旋转\",\n\"attacheType\":\"0\",\n\"filenameList\":[\n\"http://8.135.36.45:8000/find/res/images/28/20200611/03.png\"\n]\n},\n{\n\"messageId\":71,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"content\":\"阿萌赞了你的动态摩天轮旋转\",\n\"attacheType\":\"0\",\n\"filenameList\":[\n\"http://8.135.36.45:8000/find/res/images/29/20200427/014.png\"\n]\n}\n]\n}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "403": [
          {
            "group": "403",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "403",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "403",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "403错误",
          "content": "HTTP/1.1 403 403响应\n{\n\"status\": 403,\n\"code\": 199,\n\"msg\": \"未找到用户信息！\"\n}",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册！\"\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应！\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/MessageController.java",
    "groupTitle": "消息模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/message/{id}/likes"
      }
    ]
  },
  {
    "type": "delete",
    "url": "http://8.135.36.45:8084/find/message/{id}/deleteLikes",
    "title": "删除点赞消息记录接口",
    "version": "1.0.0",
    "group": "消息模块API",
    "name": "删除点赞消息记录",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>消息接收者用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "messageId",
            "description": "<p>消息记录id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT \"http://8.135.36.45:8084/find/message/60/deleteLikes?messageId=28\" -H \"accept: application/json\"",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>删除消息状态数据</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "DELETE",
            "description": "<p>OK-&gt;删除消息记录成功，ERROR-&gt;删除消息记录失败</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"标记已读成功。\",\n\"data\": {\n\"DELETE\": \"OK\"\n}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "403": [
          {
            "group": "403",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "403",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "403",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "403错误",
          "content": "HTTP/1.1 403 403响应\n{\n\"status\": 403,\n\"code\": 199,\n\"msg\": \"未找到用户信息！\"\n}",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册！\"\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应！\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/MessageController.java",
    "groupTitle": "消息模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/message/{id}/deleteLikes"
      }
    ]
  },
  {
    "type": "delete",
    "url": "http://8.135.36.45:8084/find/message/{id1}/delete",
    "title": "删除申请加微信消息记录接口",
    "version": "1.0.0",
    "group": "消息模块API",
    "name": "删除申请加微信消息记录",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id1",
            "description": "<p>消息接收者用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id2",
            "description": "<p>消息发送者用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT \"http://8.135.36.45:8084/find/message/60/delete?id2=28\" -H \"accept: application/json\"",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>删除消息状态数据</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "DELETE",
            "description": "<p>OK-&gt;删除消息记录成功，ERROR-&gt;删除消息记录失败</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"删除消息记录成功。\",\n\"data\": {\n\"DELETE\": \"OK\"\n}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "403": [
          {
            "group": "403",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "403",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "403",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "403错误",
          "content": "HTTP/1.1 403 403响应\n{\n\"status\": 403,\n\"code\": 199,\n\"msg\": \"未找到用户信息！\"\n}",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册！\"\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应！\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/MessageController.java",
    "groupTitle": "消息模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/message/{id1}/delete"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://8.135.36.45:8084/find/message/{id1}/{id2}/send",
    "title": "发送消息接口",
    "version": "1.0.0",
    "group": "消息模块API",
    "name": "发送消息",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id1",
            "description": "<p>发送者用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id2",
            "description": "<p>接收者用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "messageId",
            "description": "<p>回复的消息id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "content",
            "description": "<p>消息内容</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例 发送消息",
          "content": "HTTP/1.1 OK\ncurl -v -X POST \"http://8.135.36.45:8084/find/message/60/29/send?messageId=25&content=可以申请加你的微信吗？\" -H \"accept: application/json\"",
          "type": "json"
        },
        {
          "title": "请求示例 回复消息",
          "content": "HTTP/1.1 OK\ncurl -v -X POST \"http://8.135.36.45:8084/find/message/60/29/send?messageId=2&content=可以申请加你的微信吗？\" -H \"accept: application/json\"",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>发送状态数据</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "SEND",
            "description": "<p>OK-&gt;发送成功，ERROR-&gt;发送失败</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"发送消息成功。\",\n\"data\": {\n\"SEND\": \"OK\"\n}\n}",
          "type": "json"
        },
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"发送消息成功。\",\n\"data\": {\n\"SEND\": \"OK\"\n}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "403": [
          {
            "group": "403",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "403",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "403",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "403错误",
          "content": "HTTP/1.1 403 403响应\n{\n\"status\": 403,\n\"code\": 199,\n\"msg\": \"未找到用户信息！\"\n}",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册！\"\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应！\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/MessageController.java",
    "groupTitle": "消息模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/message/{id1}/{id2}/send"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://8.135.36.45:8084/find/message/{id}/reply",
    "title": "回复申请加微信聊天消息接口",
    "version": "1.0.0",
    "group": "消息模块API",
    "name": "回复申请加微信聊天消息",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>回复被申请加微信用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "messageId",
            "description": "<p>回复的消息id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "allowedValues": [
              "{\"0\"",
              "\"1\"}"
            ],
            "optional": false,
            "field": "type",
            "description": "<p>回复类型，0-&gt;拒绝，1-&gt;同意</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "content",
            "description": "<p>回复的消息内容</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "weChatId",
            "description": "<p>回复的微信id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例 回复申请加微信消息（拒绝）",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT \"http://8.135.36.45:8084/find/message/138/reply?messageId=37&type=0&content=非常抱歉，我不想加你！\" -H \"accept: application/json\"",
          "type": "json"
        },
        {
          "title": "请求示例 回复申请加微信消息（同意）",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT \"http://8.135.36.45:8084/find/144/reply?messageId=42&type=1&content=我乐意&weChatId=wx406151651a\" -H \"accept: application/json\"",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>回复消息状态数据</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "REPLY",
            "description": "<p>OK-&gt;回复成功，ERROR-&gt;回复失败</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"返回数据成功。\",\n\"data\": {\n\"REPLY\": \"OK\"\n}\n}",
          "type": "json"
        },
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"返回数据成功。\",\n\"data\": {\n\"REPLY\": \"OK\"\n}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "403": [
          {
            "group": "403",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "403",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "403",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "403错误",
          "content": "HTTP/1.1 403 403响应\n{\n\"status\": 403,\n\"code\": 199,\n\"msg\": \"未找到用户信息！\"\n}",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册！\"\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应！\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/MessageController.java",
    "groupTitle": "消息模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/message/{id}/reply"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://8.135.36.45:8084/find/user/{id}/uploadRegId",
    "title": "上报极光推送设备标识接口",
    "version": "1.0.0",
    "group": "用户模块API",
    "name": "上报极光推送设备标识",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": false,
            "field": "regId",
            "description": "<p>极光推送唯一设备标识</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://8.135.36.45:8084/find/user/70/uploadRegId?regId=1507bfd3f76139cd43a",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "string",
            "allowedValues": [
              "{\"OK\"",
              "\"FAILED\"}"
            ],
            "optional": true,
            "field": "UPLOADREGID",
            "description": "<p>上报状态，OK-&gt;“成功”，FAILED-&gt;“失败”</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n\t\t{\n\t\t    \"status\": 200,\n\t\t    \"code\": 0,\n\t\t    \"msg\": \"返回数据成功\",\n\t\t    \"data\": {\n\t\t        \"UPLOADREGID\": \"OK\",\n\t\t    }\n\t\t}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "403": [
          {
            "group": "403",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "403",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "403",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "403错误",
          "content": "HTTP/1.1 403 403响应\n      {\n      \t\t\"status\": 403,\n      \t\t\"code\": 199,\n      \t\t\"msg\": \"未找到用户信息\",\n      }",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\t\t\"status\": 404,\n\t\t\"code\": 200,\n\t\t\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500 错误",
          "content": "{\n\t\t\"status\": 500,\n\t\t\"code\": 205,\n\t\t\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/user/{id}/uploadRegId"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://8.135.36.45:8084/find/user/{id}/head",
    "title": "修改头像接口",
    "version": "1.0.0",
    "group": "用户模块API",
    "name": "修改头像",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "file",
            "optional": false,
            "field": "headIconFile",
            "description": "<p>头像图片文件</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例",
          "content": "HTTP/1.1 OK\n      {\n      \t\t\"headIconFile\":\"D:\\Program\\Resources\\find\\img\\head\\head01.png\"\n      }",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "file",
            "optional": true,
            "field": "head",
            "description": "<p>头像图片地址</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "id",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n\t\t{\n\t\t    \"status\": 200,\n\t\t    \"code\": 0,\n\t\t    \"msg\": \"返回数据成功\",\n\t\t    \"data\": {\n\t\t        \"head\": \"http://8.135.36.45:8000/find/img/head/2b9c022d-ec00-497c-9626-813add17b877_admin069.jpg\",\n\t\t        \"id\": 1\n\t\t    }\n\t\t}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "403": [
          {
            "group": "403",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "403",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "403",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "403错误",
          "content": "HTTP/1.1 403 403响应\n      {\n      \t\t\"status\": 403,\n      \t\t\"code\": 199,\n      \t\t\"msg\": \"未找到用户信息\",\n      }",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\t\t\"status\": 404,\n\t\t\"code\": 200,\n\t\t\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500 错误",
          "content": "{\n\t\t\"status\": 500,\n\t\t\"code\": 205,\n\t\t\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/user/{id}/head"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://8.135.36.45:8084/find/user/{id}/background",
    "title": "修改背景图接口",
    "version": "1.0.0",
    "group": "用户模块API",
    "name": "修改背景图",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "file",
            "optional": false,
            "field": "backgroundIconFile",
            "description": "<p>背景图片文件</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例",
          "content": "HTTP/1.1 OK\n      {\n      \t\t\"backgroundIconFile\":\"D:\\Program\\Resources\\find\\img\\head\\bg02.png\"\n      }",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "head",
            "description": "<p>背景图片地址</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "id",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n\t\t{\n\t\t    \"status\": 200,\n\t\t    \"code\": 0,\n\t\t    \"msg\": \"返回数据成功\",\n\t\t    \"data\": {\n\t\t        \"bg\": \"http://8.135.36.45:8000/find/img/head/2b9c022d-ec00-497c-9626-813add17b877_admin069.jpg\",\n\t\t        \"id\": 1\n\t\t    }\n\t\t}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "403": [
          {
            "group": "403",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "403",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "403",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "403错误",
          "content": "HTTP/1.1 403 403响应\n      {\n      \t\t\"status\": 403,\n      \t\t\"code\": 199,\n      \t\t\"msg\": \"未找到用户信息\",\n      }",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\t\t\"status\": 404,\n\t\t\"code\": 200,\n\t\t\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500 错误",
          "content": "HTTP/1.1 500 500响应\n{\n\t\t\"status\": 500,\n\t\t\"code\": 205,\n\t\t\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/user/{id}/background"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://8.135.36.45:8084/find/user/isreg?phone={手机号码}",
    "title": "判断手机号是否注册接口",
    "version": "1.0.0",
    "group": "用户模块API",
    "name": "判断手机号是否注册",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "11",
            "optional": false,
            "field": "phone",
            "description": "<p>手机号码，必须是11位数字符合要求的手机号码</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例（判断手机号是否注册）",
          "content": "HTTP/1.1 OK\ncurl -v -X GET http://8.135.36.45:8084/find/user/isreg?phone=18138802541",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "boolean",
            "allowedValues": [
              "\"true\"",
              "\"false\""
            ],
            "optional": true,
            "field": "isReg",
            "description": "<p>用户是否已经注册，true-&gt;已经注册，false-&gt;还未注册</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n\t\t{\n\t\t    \"status\": 200,\n\t\t    \"code\": 0,\n\t\t    \"msg\": \"SUCCESS，还未注册。\",\n\t\t    \"data\": {\n\t\t        \"isReg\": false\n\t\t    }\n\t\t}",
          "type": "json"
        },
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n\t\t{\n\t\t    \"status\": 200,\n\t\t    \"code\": 0,\n\t\t    \"msg\": \"SUCCESS，已经注册。\",\n\t\t    \"data\": {\n\t\t        \"isReg\": true\n\t\t    }\n\t\t}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\t\t\"status\": 404,\n\t\t\"code\": 200,\n\t\t\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\t\t\"status\": 500,\n\t\t\"code\": 205,\n\t\t\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/user/isreg?phone={手机号码}"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://8.135.36.45:8084/find/user/{id}/pushOrPull",
    "title": "拉入推出黑名单接口",
    "version": "1.0.0",
    "group": "用户模块API",
    "name": "拉入推出黑名单接口",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "blackUserId",
            "description": "<p>黑名单用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "int",
            "optional": false,
            "field": "type",
            "description": "<p>奇数-&gt;拉入，偶数-&gt;推出</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例",
          "content": "HTTP/1.1 OK\ncurl --insecure -X POST -v http://8.135.36.45:8084/find/user/1/pushOrPull -H \"Content-Type: application/json;charset=UTF-8\" -d '{\"blackUserId\":2, \"type\":0}'",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "PULL",
            "description": "<p>推出状态</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "PUSH",
            "description": "<p>拉入状态</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例 推出黑名单列表",
          "content": "HTTP/1.1 200 OK\n\t\t{\n\t\t    \"status\": 200,\n\t\t    \"code\": 0,\n\t\t    \"msg\": \"将用户千柳推出黑名单列表成功。\",\n\t\t    \"data\": {\n\t\t        \"PULL\": \"OK\"\n\t\t    }\n\t\t}",
          "type": "json"
        },
        {
          "title": "200响应示例 拉入黑名单列表",
          "content": "HTTP/1.1 200 OK\n\t\t{\n\t\t    \"status\": 200,\n\t\t    \"code\": 0,\n\t\t    \"msg\": \"将用户千柳拉入黑名单列表成功。\",\n\t\t    \"data\": {\n\t\t        \"PUSH\": \"OK\"\n\t\t    }\n\t\t}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\t\t\"status\": 404,\n\t\t\"code\": 200,\n\t\t\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\t\t\"status\": 500,\n\t\t\"code\": 205,\n\t\t\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/user/{id}/pushOrPull"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://8.135.36.45:8084/find/user/{id}/update",
    "title": "更新用户资料接口",
    "version": "1.0.0",
    "group": "用户模块API",
    "name": "更新用户资料",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "nickname",
            "description": "<p>昵称</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "weixinId",
            "description": "<p>微信号</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..4",
            "optional": true,
            "field": "year",
            "description": "<p>出生年代</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..2",
            "optional": true,
            "field": "month",
            "description": "<p>出生月份</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..2",
            "optional": true,
            "field": "date",
            "description": "<p>出生日期</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..4",
            "optional": true,
            "field": "constellation",
            "defaultValue": "{\"水瓶座\",\"双鱼座\",\"白羊座\",\"金牛座\",\"双子座\",\"巨蟹座\",\"狮子座\",\"处女座\",\"天秤座\",\"天蝎座\",\"射手座\",\"摩羯座\"}",
            "description": "<p>星座</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "autograph",
            "description": "<p>签名</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例 修改昵称",
          "content": "HTTP/1.1 OK\ncurl --insecure -X PUT -v http://8.135.36.45:8084/find/user/{id}/update -H \"Content-Type: application/json;;charset=UTF-8\" -d '{\"nickname\":\"王6\"}'",
          "type": "json"
        },
        {
          "title": "请求示例 修改微信号",
          "content": "HTTP/1.1 OK\ncurl --insecure -X PUT -v http://8.135.36.45:8084/find/user/{id}/update -H \"Content-Type: application/json;charset=UTF-8\" -d '{\"weixinId\":\"12622\"}'",
          "type": "json"
        },
        {
          "title": "请求示例 修改出生年月日和星座",
          "content": "HTTP/1.1 OK\ncurl --insecure -X PUT -v http://8.135.36.45:8084/find/user/{id}/update -H \"Content-Type: application/json;charset=UTF-8\" -d '{\"year\":\"1996\", \"month\":\"08\", \"date\":\"03\", \"constellation\":\"双子座\"}'",
          "type": "json"
        },
        {
          "title": "请求示例 修改签名",
          "content": "HTTP/1.1 OK\ncurl --insecure -X PUT -v http://8.135.36.45:8084/find/user/{id}/update -H \"Content-Type: application/json;charset=UTF-8\" -d '{\"autograph\":\"12622\"}'",
          "type": "json"
        },
        {
          "title": "请求示例 修改签名和出生年月日，星座",
          "content": "HTTP/1.1 OK\ncurl --insecure -X PUT -v http://8.135.36.45:8084/find/user/{id}/update -H \"Content-Type: application/json;charset=UTF-8\" -d '{\"autograph\":\"126我的2\", \"year\":\"1996\", \"month\":\"08\", \"date\":\"03\", \"constellation\":\"水平座\"}'",
          "type": "json"
        },
        {
          "title": "请求示例 修改签名，出生年月日，星座，昵称，微信号",
          "content": "HTTP/1.1 OK\ncurl --insecure -X PUT -v http://8.135.36.45:8084/find/user/{id}/update -H \"Content-Type: application/json;charset=UTF-8\" -d '{\"nickname\":\"王666\", \"weixinId\":\"12622www\", \"autograph\":\"126我的ss2\", \"year\":\"1996\", \"month\":\"08\", \"date\":\"03\", \"constellation\":\"射手座\"}'",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n\t\t{\n\t\t    \"status\": 200,\n\t\t    \"code\": 0,\n\t\t    \"msg\": \"修改或者更新用户资料成功\",\n\t\t    \"data\": {\n\t\t        \"UPDATE\": \"OK\"\n\t\t    }\n\t\t}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\t\t\"status\": 404,\n\t\t\"code\": 200,\n\t\t\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\t\t\"status\": 500,\n\t\t\"code\": 205,\n\t\t\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/user/{id}/update"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://8.135.36.45:8084/find/user/{id}/queryWeixin",
    "title": "查看用户微信号接口",
    "version": "1.0.0",
    "group": "用户模块API",
    "name": "查看用户微信号",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例 修改昵称",
          "content": "HTTP/1.1 OK\ncurl --insecure -X GET -v http://8.135.36.45:8084/find/user/1/queryWeixin",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "user",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "weixinId",
            "description": "<p>微信号</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n\t\t{\n\t\t    \"status\": 200,\n\t\t    \"code\": 0,\n\t\t    \"msg\": \"查看用户个人资料成功\",\n\t\t    \"data\": {\n\t\t        \"user\": {\n\t\t            \"weixinId\": 11111111\n\t\t        }\n\t\t    }\n\t\t}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\t\t\"status\": 404,\n\t\t\"code\": 200,\n\t\t\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\t\t\"status\": 500,\n\t\t\"code\": 205,\n\t\t\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/user/{id}/queryWeixin"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://8.135.36.45:8084/find/user/{id}/query",
    "title": "查看用户资料接口",
    "version": "1.0.0",
    "group": "用户模块API",
    "name": "查看用户资料",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例 获取用户资料",
          "content": "HTTP/1.1 OK\ncurl --insecure -X GET -v http://8.135.36.45:8084/find/user/1/query",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "user",
            "description": "<p>用户数据</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "nickname",
            "description": "<p>昵称</p>"
          },
          {
            "group": "200",
            "type": "int",
            "allowedValues": [
              "{\"0\"",
              "\"1\"",
              "\"2\"}"
            ],
            "optional": true,
            "field": "grade",
            "description": "<p>VIP等级，0-&gt;普通用户，1-&gt;VIP1等级用户，2-&gt;VIP2等级用户</p>"
          },
          {
            "group": "200",
            "type": "int",
            "optional": true,
            "field": "age",
            "description": "<p>年龄</p>"
          },
          {
            "group": "200",
            "type": "string",
            "allowedValues": [
              "{\"0\"",
              "\"1\"}"
            ],
            "optional": true,
            "field": "gender",
            "description": "<p>性别，&quot;0&quot;-&gt;女生，&quot;1&quot;-&gt;男生</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "head",
            "description": "<p>头像</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "bg",
            "description": "<p>背景图片</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "autograph",
            "description": "<p>签名</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n\t\t{\n\t\t    \"status\": 200,\n\t\t    \"code\": 0,\n\t\t    \"msg\": \"查看用户个人资料成功\",\n\t\t    \"data\": {\n\t\t        \"user\": {\n\t\t            \"id\": 1,\n\t\t            \"nickname\": \"王666\",\n\t\t            \"head\": \"http://8.135.36.45:8000/find/img/head/2b9c022d-ec00-497c-9626-813add17b877_admin069.jpg\",\n\t\t            \"bg\": \"http://8.135.36.45:8000/find/img/background/a1bf6181-ebd0-43b4-8e91-761ec8fc83ab_admin055.jpg\",\n\t\t            \"grade\": 0,\n\t\t            \"age\": 24,\n\t\t            \"gender\": \"1\",\n\t\t            \"autograph\": \"126我的ss2\"\n\t\t        }\n\t\t    }\n\t\t}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\t\t\"status\": 404,\n\t\t\"code\": 200,\n\t\t\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\t\t\"status\": 500,\n\t\t\"code\": 205,\n\t\t\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/user/{id}/query"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://8.135.36.45:8084/find/user/{id}/report",
    "title": "用户举报接口",
    "version": "1.0.0",
    "group": "用户模块API",
    "name": "用户举报",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>举报用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "categoryId",
            "description": "<p>举报类目id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "int",
            "allowedValues": [
              "\"1\"",
              "\"2\""
            ],
            "optional": false,
            "field": "reportType",
            "description": "<p>举报类型，1-&gt;动态，2-&gt;用户</p>"
          },
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "beingReportId",
            "description": "<p>被举报用户id或者动态id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": false,
            "field": "reportContent",
            "description": "<p>举报内容</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例 查看用户举报类型",
          "content": "HTTP/1.1 OK\ncurl --insecure -X POST -v http://8.135.36.45:8084/find/user/{id}/report -H \"Content-Type: application/json;charset=UTF-8\" -d '{\"categoryId\":1, \"reportType\":1, \"beingReportId\":3, \"reportContent\":\"老是打广告dddddd+++++++！！！！！\"}'",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "REPORTED",
            "description": "<p>举报消息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n\t\t{\n\t\t    \"status\": 200,\n\t\t    \"code\": 0,\n\t\t    \"msg\": \"记录用户举报内容成功。\",\n\t\t    \"data\": {\n\t\t        \"REPORTED\": \"OK\"\n\t\t    }\n\t\t}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\t\t\"status\": 404,\n\t\t\"code\": 200,\n\t\t\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\t\t\"status\": 500,\n\t\t\"code\": 205,\n\t\t\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/user/{id}/report"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://8.135.36.45:8084/find/user/reg",
    "title": "用户注册接口",
    "version": "1.0.0",
    "group": "用户模块API",
    "name": "用户注册",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "11",
            "optional": false,
            "field": "phone",
            "description": "<p>手机号码</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "16",
            "optional": true,
            "field": "ip",
            "description": "<p>客户端IP不能与定位（国家、省份，城市）同时为空</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "1",
            "allowedValues": [
              "\"0\"",
              "\"1\""
            ],
            "optional": false,
            "field": "gender",
            "description": "<p>性别，0-&gt;女生；1-&gt;男生</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "1..16",
            "optional": true,
            "field": "platform",
            "description": "<p>平台</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..32",
            "optional": false,
            "field": "nickname",
            "description": "<p>昵称</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..128",
            "optional": false,
            "field": "weixinId",
            "description": "<p>微信号</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..64",
            "optional": true,
            "field": "imei",
            "description": "<p>设备串码</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..64",
            "optional": true,
            "field": "model",
            "description": "<p>设备型号</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..16",
            "optional": true,
            "field": "sysName",
            "description": "<p>系统名称</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..16",
            "optional": true,
            "field": "sysCode",
            "description": "<p>系统版本</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..4",
            "optional": true,
            "field": "networkMode",
            "description": "<p>网络方式</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..4",
            "optional": false,
            "field": "year",
            "description": "<p>出生年份</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..2",
            "optional": false,
            "field": "month",
            "description": "<p>出生月份</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..2",
            "optional": false,
            "field": "date",
            "description": "<p>出生日期</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "allowedValues": [
              "{\"水瓶座\"",
              "\"双鱼座\"",
              "\"白羊座\"",
              "\"金牛座\"",
              "\"双子座\"",
              "\"巨蟹座\"",
              "\"狮子座\"",
              "\"处女座\"",
              "\"天秤座\"",
              "\"天蝎座\"",
              "\"射手座\"",
              "\"摩羯座\"}"
            ],
            "optional": false,
            "field": "constellation",
            "description": "<p>星座</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..16",
            "optional": true,
            "field": "country",
            "description": "<p>定位（国家）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..32",
            "optional": true,
            "field": "province",
            "description": "<p>定位（省份）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..32",
            "optional": true,
            "field": "city",
            "description": "<p>定位（城市）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..255",
            "optional": true,
            "field": "autograph",
            "description": "<p>签名/发布动态内容</p>"
          },
          {
            "group": "接口请求参数",
            "type": "file",
            "optional": false,
            "field": "head",
            "description": "<p>头像图片文件</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例01（注册01）",
          "content": "HTTP/1.1 OK 封装表单数据格式01 注：form表单提交，需要在请求头加：“Content-Type=multipart/form-data;charset=utf-8”\n  \t\t\"phone\" : \"18138812110\",\n  \t\t\"ip\" : \"183.14.29.70\",\n \t\t\"gender\" : \"1\",\n  \t\t\"platform\" : \"Baidu\",\n  \t\t\"nickname\" : \"张三\",\n  \t\t\"weixinId\" : \"wx123456788\",\n  \t\t\"imei\" : \"123456788222\",\n  \t\t\"model\" : \"vivo Y55\",\n  \t\t\"sysName\" : \"Android\",\n  \t\t\"sysCode\" : \"6.0.1\",\n \t\t\"networkMode\" : \"WIFI\",\n  \t\t\"year\" : \"1995\",\n  \t\t\"month\" : \"05\",\n  \t\t\"date\" : \"05\",\n  \t\t\"constellation\" : \"巨蟹座\",\n \t\t\"autograph\" : \"新人来到，多多关照，谢谢！\",\n \t\t\"head\":\"D:\\Program\\Resources\\find\\img\\head\\02.png\"",
          "type": "json"
        },
        {
          "title": "请求示例02（注册02）",
          "content": "HTTP/1.1 OK 封装表单数据格式02 注：form表单提交，需要在请求头加：“Content-Type=multipart/form-data;charset=utf-8”\n  \t\t\"phone\" : \"18138812310\",\n  \t\t\"ip\" : \"123.84.129.170\",\n  \t\t\"gender\" : \"0\",\n  \t\t\"platform\" : \"HUAWEI\",\n  \t\t\"nickname\" : \"李四\",\n  \t\t\"weixinId\" : \"wx123456700\",\n  \t\t\"imei\" : \"123456788111\",\n  \t\t\"model\" : \"vivo Z5\",\n  \t\t\"sysName\" : \"Android\",\n  \t\t\"sysCode\" : \"9.0.1\",\n  \t\t\"networkMode\" : \"WIFI\",\n  \t\t\"year\" : \"1993\",\n  \t\t\"month\" : \"05\",\n  \t\t\"date\" : \"05\",\n  \t\t\"constellation\" : \"巨蟹座\",\n  \t\t\"autograph\" : \"新人来到，多多关照，谢谢！\",\n  \t\t\"country\" : \"中国\",\n  \t\t\"province\" : \"广东\",\n  \t\t\"city\" : \"广州\",\n  \t\t\"head\":\"D:\\Program\\Resources\\find\\img\\head\\02.png\"",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "user",
            "description": "<p>用户数据</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "gender",
            "description": "<p>性别</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "nickname",
            "description": "<p>昵称</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "head",
            "description": "<p>头像图片地址</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "bg",
            "description": "<p>背景图片地址</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "autograph",
            "description": "<p>签名</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n\t\t{\n\t\t    \"status\": 200,\n\t\t    \"code\": 0,\n\t\t    \"msg\": \"登录或者注册成功。\",\n\t\t    \"data\": {\n\t\t        \"user\": {\n\t\t            \"id\": 21,\n\t\t            \"gender\": \"1\",\n\t\t            \"nickname\": \"张三\",\n\t\t            \"head\": \"http://8.135.36.45:8000/find/img/head/head.png\",\n\t\t            \"bg\": \"http://8.135.36.45:8000/find/img/background/bg.png\",\n\t\t            \"autograph\": \"新人小生，初来乍到，请多关照。\"\n\t\t        }\n\t\t    }\n\t\t}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "403": [
          {
            "group": "403",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "403",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "403",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "403错误",
          "content": "HTTP/1.1 403 403响应\n      {\n      \t\t\"status\": 403,\n      \t\t\"code\": 199,\n      \t\t\"msg\": \"未找到用户信息！\",\n      }",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n      {\n      \t\t\"status\": 404,\n     \t\t\"code\": 200,\n      \t\t\"msg\": \"接口未注册！\",\n      }",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n      {\n      \t\t\"status\": 500,\n      \t\t\"code\": 205,\n      \t\t\"msg\": \"服务器未响应！\"\n      }",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/user/reg"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://8.135.36.45:8084/find/user/login",
    "title": "用户登录接口",
    "version": "1.0.0",
    "group": "用户模块API",
    "name": "用户登录",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "11",
            "optional": false,
            "field": "phone",
            "description": "<p>手机号码</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "16",
            "optional": true,
            "field": "ip",
            "description": "<p>客户端IP不能与定位（国家、省份，城市）同时为空</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..16",
            "optional": true,
            "field": "country",
            "description": "<p>定位（国家）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..32",
            "optional": true,
            "field": "province",
            "description": "<p>定位（省份）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..32",
            "optional": true,
            "field": "city",
            "description": "<p>定位（城市）</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例01（手机号码和客户端IP登录）",
          "content": "HTTP/1.1 OK\ncurl --insecure -X PUT -v http://8.135.36.45:8084/find/user/login?phone=18138812310&ip=183.14.29.70",
          "type": "json"
        },
        {
          "title": "请求示例02（手机号码和定位地址登录）",
          "content": "HTTP/1.1 OK\ncurl --insecure -X PUT -v http://8.135.36.45:8084/find/user/login?phone=18138812236&country=中国&province=广东省&city=深圳市",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "user",
            "description": "<p>用户数据</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "gender",
            "description": "<p>性别</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "nickname",
            "description": "<p>昵称</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "head",
            "description": "<p>头像图片地址</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "bg",
            "description": "<p>背景图片地址</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "autograph",
            "description": "<p>签名</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n\t\t{\n\t\t    \"status\": 200,\n\t\t    \"code\": 0,\n\t\t    \"msg\": \"登录成功。\",\n\t\t    \"data\": {\n\t\t        \"user\": {\n\t\t            \"id\": 21,\n\t\t            \"gender\": \"1\",\n\t\t            \"nickname\": \"张三\",\n\t\t            \"head\": \"http://8.135.36.45:8000/find/img/head/head.png\",\n\t\t            \"bg\": \"http://8.135.36.45:8000/find/img/background/bg.png\",\n\t\t            \"autograph\": \"新人小生，初来乍到，请多关照。\"\n\t\t        }\n\t\t    }\n\t\t}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "403": [
          {
            "group": "403",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "403",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "403",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "403错误",
          "content": "HTTP/1.1 403 403响应\n      {\n      \t\t\"status\": 403,\n      \t\t\"code\": 199,\n      \t\t\"msg\": \"未找到用户信息！\",\n      }",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n      {\n      \t\t\"status\": 404,\n     \t\t\"code\": 200,\n      \t\t\"msg\": \"接口未注册！\",\n      }",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n      {\n      \t\t\"status\": 500,\n      \t\t\"code\": 205,\n      \t\t\"msg\": \"服务器未响应！\"\n      }",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/user/login"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://8.135.36.45:8084/find/user/{id}/report-categories",
    "title": "获取用户举报类型接口",
    "version": "1.0.0",
    "group": "用户模块API",
    "name": "获取用户举报类型",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例 查看用户举报类型",
          "content": "HTTP/1.1 OK\ncurl --insecure -X GET -v http://8.135.36.45:8084/find/user/1/report-categories",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "object[]",
            "optional": true,
            "field": "list",
            "description": "<p>举报类型数据列表</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "id",
            "description": "<p>类型id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "name",
            "description": "<p>类型名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n\t\t{\n\t\t    \"status\": 200,\n\t\t    \"code\": 0,\n\t\t    \"msg\": \"获取用户举报类型列表成功\",\n\t\t    \"data\": {\n\t\t        \"list\": [\n\t\t            {\n\t\t                \"id\": 1,\n\t\t                \"name\": \"垃圾广告\"\n\t\t            },\n\t\t            {\n\t\t                \"id\": 2,\n\t\t                \"name\": \"色情低俗\"\n\t\t            },\n\t\t            {\n\t\t                \"id\": 3,\n\t\t                \"name\": \"政治敏感\"\n\t\t            },\n\t\t            {\n\t\t                \"id\": 4,\n\t\t                \"name\": \"恐怖暴力\"\n\t\t            },\n\t\t            {\n\t\t                \"id\": 5,\n\t\t                \"name\": \"违法暴力\"\n\t\t            },\n\t\t            {\n\t\t                \"id\": 6,\n\t\t                \"name\": \"自杀自残\"\n\t\t            },\n\t\t            {\n\t\t                \"id\": 7,\n\t\t                \"name\": \"侵犯个人信息\"\n\t\t            },\n\t\t            {\n\t\t                \"id\": 8,\n\t\t                \"name\": \"侵犯未成年人权益\"\n\t\t            }\n\t\t        ]\n\t\t    }\n\t\t}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\t\t\"status\": 404,\n\t\t\"code\": 200,\n\t\t\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\t\t\"status\": 500,\n\t\t\"code\": 205,\n\t\t\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/user/{id}/report-categories"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://8.135.36.45:8084/find/user/{id}/blacklist?pageNum={pageNum}&pageSize={pageSize}",
    "title": "获取用户黑名单列表接口",
    "version": "1.0.0",
    "group": "用户模块API",
    "name": "获取用户黑名单列表接口",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "int",
            "optional": true,
            "field": "pageNum",
            "description": "<p>当前页数，默认值：1</p>"
          },
          {
            "group": "接口请求参数",
            "type": "int",
            "optional": true,
            "field": "pageSize",
            "description": "<p>每页条数，默认值：10</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例 查看用户举报类型",
          "content": "HTTP/1.1 OK\ncurl --insecure -X GET -v http://8.135.36.45:8084/find/user/1/blacklist?pageNum=1&pageSize=10",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "int",
            "optional": true,
            "field": "totalPage",
            "description": "<p>总页数</p>"
          },
          {
            "group": "200",
            "type": "object[]",
            "optional": true,
            "field": "list",
            "description": "<p>黑名单数据列表</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "nickname",
            "description": "<p>昵称</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "head",
            "description": "<p>头像图片地址</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "time",
            "description": "<p>加入时间</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n\t\t{\n\t\t    \"status\": 200,\n\t\t    \"code\": 0,\n\t\t    \"msg\": \"获取用户黑名单列表成功。\",\n\t\t    \"data\": {\n\t\t        \"totalPage\": 2,\n\t\t        \"list\": [\n\t\t            {\n\t\t                \"id\": 12,\n\t\t                \"nickname\": \"陆蕙兰\",\n\t\t                \"head\": \"http://8.135.36.45:8000/find/img/head/12.jpg\",\n\t\t                \"time\": \"2021年01月22日 15:25:27\"\n\t\t            },\n\t\t            {\n\t\t                \"id\": 11,\n\t\t                \"nickname\": \"商梓珊\",\n\t\t                \"head\": \"http://8.135.36.45:8000/find/img/head/11.jpg\",\n\t\t                \"time\": \"2021年01月22日 15:24:51\"\n\t\t            },\n\t\t            {\n\t\t                \"id\": 10,\n\t\t                \"nickname\": \"赫飞雨\",\n\t\t                \"head\": \"http://8.135.36.45:8000/find/img/head/10.jpg\",\n\t\t                \"time\": \"2021年01月22日 15:24:47\"\n\t\t            },\n\t\t            {\n\t\t                \"id\": 9,\n\t\t                \"nickname\": \"寒烟\",\n\t\t                \"head\": \"http://8.135.36.45:8000/find/img/head/09.jpg\",\n\t\t                \"time\": \"2021年01月22日 15:24:42\"\n\t\t            },\n\t\t            {\n\t\t                \"id\": 7,\n\t\t                \"nickname\": \"勾高朗\",\n\t\t                \"head\": \"http://8.135.36.45:8000/find/img/head/07.jpg\",\n\t\t                \"time\": \"2021年01月22日 15:24:30\"\n\t\t            },\n\t\t            {\n\t\t                \"id\": 6,\n\t\t                \"nickname\": \"慕蕊\",\n\t\t                \"head\": \"http://8.135.36.45:8000/find/img/head/06.jpg\",\n\t\t                \"time\": \"2021年01月22日 15:24:24\"\n\t\t            },\n\t\t            {\n\t\t                \"id\": 5,\n\t\t                \"nickname\": \"庾音韵\",\n\t\t                \"head\": \"http://8.135.36.45:8000/find/img/head/05.jpg\",\n\t\t                \"time\": \"2021年01月22日 15:24:19\"\n\t\t            },\n\t\t            {\n\t\t                \"id\": 4,\n\t\t                \"nickname\": \"覃星宇\",\n\t\t                \"head\": \"http://8.135.36.45:8000/find/img/head/04.jpg\",\n\t\t                \"time\": \"2021年01月22日 15:24:14\"\n\t\t            },\n\t\t            {\n\t\t                \"id\": 3,\n\t\t                \"nickname\": \"致命德毒药\",\n\t\t                \"head\": \"http://8.135.36.45:8000/find/img/head/03.jpg\",\n\t\t                \"time\": \"2021年01月22日 15:24:08\"\n\t\t            },\n\t\t            {\n\t\t                \"id\": 2,\n\t\t                \"nickname\": \"千柳\",\n\t\t                \"head\": \"http://8.135.36.45:8000/find/img/head/02.jpg\",\n\t\t                \"time\": \"2021年01月22日 15:24:02\"\n\t\t            }\n\t\t        ]\n\t\t    }\n\t\t}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\t\t\"status\": 404,\n\t\t\"code\": 200,\n\t\t\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\t\t\"status\": 500,\n\t\t\"code\": 205,\n\t\t\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/user/{id}/blacklist?pageNum={pageNum}&pageSize={pageSize}"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://8.135.36.45:8084/find/order/{id}/mobile/create",
    "title": "创建预支付订单接口",
    "version": "1.0.0",
    "group": "订单模块API",
    "name": "创建预支付订单",
    "parameter": {
      "fields": {
        "请求参数": [
          {
            "group": "请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "请求参数",
            "type": "long",
            "optional": false,
            "field": "pid",
            "description": "<p>商品id</p>"
          },
          {
            "group": "请求参数",
            "type": "int",
            "optional": false,
            "field": "mode",
            "description": "<p>支付方式：0-&gt;微信，1-&gt;支付宝</p>"
          },
          {
            "group": "请求参数",
            "type": "string",
            "optional": true,
            "field": "ip",
            "description": "<p>客户端IP</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例01（微信支付）",
          "content": "HTTP/1.1 OK\ncurl -v -X POST http://8.135.36.45:8084/find/order/1/mobile/create -H \"Content-Type:application/json;charset=utf-8\" -d '{\"pid\":1, \"mode\":0, \"ip\":\"127.0.0.1\"}'",
          "type": "json"
        },
        {
          "title": "请求示例02（支付宝支付）",
          "content": "HTTP/1.1 OK\ncurl -v -X POST http://8.135.36.45:8084/find/order/1/mobile/create -H \"Content-Type:application/json;charset=utf-8\" -d '{\"pid\":1, \"mode\":1, \"ip\":\"127.0.0.1\"}'",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "trade_info",
            "description": "<p>商品预支付订单信息</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "appid",
            "description": "<p>应用id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "err_code",
            "description": "<p>错误码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "err_code_des",
            "description": "<p>错误说明</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "out_trade_no",
            "description": "<p>订单号</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "package",
            "description": "<p>支付标识</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "partnerid",
            "description": "<p>商户id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "prepayid",
            "description": "<p>预支付请求id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "result_code",
            "description": "<p>结果状态</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "return_code",
            "description": "<p>返回状态</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "return_msg",
            "description": "<p>返回消息说明</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "sign",
            "description": "<p>签名</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "timestamp",
            "description": "<p>时间戳</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "orderString",
            "description": "<p>支付订单信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200（微信支付）响应示例01",
          "content": "HTTPS/1.1 200 OK\n{\n\"code\": 0,\n\"data\": {\n\"trade_info\": {\n\"appid\": \"wx394471ab93938b34\",\n\"err_code\": \"Success\",\n\"err_code_des\": \"请求微信支付统一下单接口生成APP支付预付单信息成功。\",\n\"noncestr\": \"1610960641587\",\n\"out_trade_no\": \"2021011817040003581135ba8bfa742a\",\n\"package\": \"Sign=WXPay\",\n\"partnerid\": \"1539515591\",\n\"prepayid\": \"wx1817040283097360129d35d33dfc5a0000\",\n\"result_code\": \"Success\",\n\"return_code\": \"Success\",\n\"return_msg\": \"生成微信APP支付预支付订单信息成功。\",\n\"sign\": \"0F6E2CB474B2A6B675D35D3F9215086D\",\n\"timestamp\": \"1610960641\"\n}\n},\n\"msg\": \"返回数据成功\",\n\"status\": 200\n}",
          "type": "json"
        },
        {
          "title": "200（支付宝支付）响应示例01",
          "content": "HTTPS/1.1 200 OK\n{\n\"code\": 0,\n\"data\": {\n\"trade_info\": {\n\"appid\": \"2021001183634710\",\n\"err_code\": \"Success\",\n\"err_code_des\": \"生成支付宝APP预支付订单信息返回数据成功。\",\n\"orderString\": \"alipay_sdk=alipay-sdk-java-3.7.110.ALL&app_id=2021001183634710&biz_content=%7B%22goods_type%22%3A%220%22%2C%22out_trade_no%22%3A%22202101181714518933f2c2fe0f3fa423%22%2C%22subject%22%3A%22%E5%85%85%E5%80%BC2%E4%B8%AA%E6%9C%88VIP%22%2C%22timeout_express%22%3A%2230%22%2C%22total_amount%22%3A%221%22%7D&charset=UTF-8&format=JSON&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fw168428j19.51mypc.cn%2Ffind%2Fv1%2Forder%2Fpay%2Falipay-notify.do&sign=Hi4DY84hHqM%2F3SrBCCtUYfDd9i8ZKrf8QF0O3nRRO0bgvS7GTyuLOhaJQ9Td%2FLMfsvU7G0OPh7PABaslzRLqRKdVrMe0LvrVtBQQJ2%2BKh0w0YGOSoIV7tGq%2Bkz2hs4%2FmH%2FLfLH2XX2tSDOi3HM6CdhhF7SkX7DFEbgowLGR3VtRVpbKVBtpHKHVk%2BQbxlPnkiDNb9u6bnefb2kBYJB6AGLL7E7PrwQOx61yezFg8HBAd7Ic%2FNstMEZ%2BX5ZcCSQaiRbaAQ5iZTTiFiyF66bHtTTYrlT2M37JxO6VQ2o5Rn4EOKS4d1NwqaqUnGg7upZH4ygqbZFkaAvDGo3bbS%2FnZ0Q%3D%3D&sign_type=RSA2&timestamp=2021-01-18+17%3A14%3A51&version=2.0\",\n\"out_trade_no\": \"202101181714518933f2c2fe0f3fa423\",\n\"result_code\": \"Success\",\n\"return_code\": \"Success\",\n\"return_msg\": \"生成支付宝APP预支付订单信息返回数据成功。\"\n}\n},\n\"msg\": \"返回数据成功\",\n\"status\": 200\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "403": [
          {
            "group": "403",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "403",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "403",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "403错误",
          "content": "HTTP/1.1 403 403响应\n{\n\"status\": 403,\n\"code\": 199,\n\"msg\": \"未找到用户信息！\",\n}",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册！\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应！\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/OrderController.java",
    "groupTitle": "订单模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/order/{id}/mobile/create"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://8.135.36.45:8084/find/order/{id}/product/list",
    "title": "获取充值商品列表接口",
    "version": "1.0.0",
    "group": "订单模块API",
    "name": "获取充值商品列表",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例03（手机号码和客户端IP登录）",
          "content": "HTTP/1.1 OK\ncurl -v -X GET http://8.135.36.45:8084/find/order/1/product/list",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>信息码</p>"
          },
          {
            "group": "200",
            "type": "string",
            "size": "..255",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          },
          {
            "group": "200",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "200",
            "type": "object",
            "optional": true,
            "field": "data",
            "description": "<p>数据</p>"
          },
          {
            "group": "200",
            "type": "object[]",
            "optional": true,
            "field": "list",
            "description": "<p>商品信息列表数据</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "desc",
            "description": "<p>描述</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "icon",
            "description": "<p>商品小图地址</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "name",
            "description": "<p>商品名称</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "pid",
            "description": "<p>商品id</p>"
          },
          {
            "group": "200",
            "type": "double",
            "optional": true,
            "field": "price",
            "description": "<p>商品价格</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTPS/1.1 200 OK\n{\n\"code\": 0,\n\"msg\": \"查询充值会员时长套餐产品列表成功\",\n\"status\": 200,\n\"data\": {\n\"list\": [\n{\n\"desc\": \"60元/月\",\n\"icon\": \"http://8.135.36.45:8000/find/img/app/product/01.png\",\n\"name\": \"2个月\",\n\"pid\": 1,\n\"price\": 120.00\n},\n{\n\"desc\": \"50元/月\",\n\"icon\": \"http://8.135.36.45:8000/find/img/app/product/02.png\",\n\"name\": \"3个月\",\n\"pid\": 2,\n\"price\": 180.00\n},\n{\n\"desc\": \"41.3元/月\",\n\"icon\": \"http://8.135.36.45:8000/find/img/app/product/03.png\",\n\"name\": \"6个月\",\n\"pid\": 3,\n\"price\": 248.00\n},\n{\n\"desc\": \"33元/月\",\n\"icon\": \"http://8.135.36.45:8000/find/img/app/product/04.png\",\n\"name\": \"12个月\",\n\"pid\": 4,\n\"price\": 369.00\n}\n]\n}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "403": [
          {
            "group": "403",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "403",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "403",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "404": [
          {
            "group": "404",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "404",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "404",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ],
        "500": [
          {
            "group": "500",
            "type": "int",
            "size": "0-65535",
            "optional": false,
            "field": "status",
            "description": "<p>响应状态码</p>"
          },
          {
            "group": "500",
            "type": "long",
            "size": "0-500",
            "optional": false,
            "field": "code",
            "description": "<p>消息码</p>"
          },
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "403错误",
          "content": "HTTP/1.1 403 403响应\n{\n\"status\": 403,\n\"code\": 199,\n\"msg\": \"未找到用户信息！\",\n}",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册！\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应！\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/OrderController.java",
    "groupTitle": "订单模块API",
    "sampleRequest": [
      {
        "url": "http://8.135.36.45:8084/find/order/{id}/product/list"
      }
    ]
  }
] });
