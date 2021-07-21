define({ "api": [
  {
    "type": "put",
    "url": "http://127.0.0.1:8084/find/dynamic/{id}/share",
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
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://127.0.0.1:8084/find/dynamic/70/share?dynamicInfoId=86&mode=0",
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
        "url": "http://127.0.0.1:8084/find/dynamic/{id}/share"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://127.0.0.1:8084/find/comment/{id}/query",
    "title": "分页查询评论接口",
    "version": "1.0.0",
    "group": "动态模块API",
    "name": "分页查询评论接口",
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
            "type": "int",
            "optional": true,
            "field": "pageNum",
            "description": "<p>当前页数，默认：1</p>"
          },
          {
            "group": "接口请求参数",
            "type": "int",
            "optional": true,
            "field": "pageSize",
            "description": "<p>每页条数，默认：20</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例",
          "content": "HTTP/1.1 OK\ncurl -v -X GET http://127.0.0.1:8084/find/comment/35/query?dynamicInfoId=1&pageNum=1&pageSize=20",
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
            "type": "long",
            "optional": true,
            "field": "totalSize",
            "description": "<p>总条数</p>"
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
            "description": "<p>评论数据列表</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "userId",
            "description": "<p>评论的用户id</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "commentId",
            "description": "<p>评论id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "nickname",
            "description": "<p>评论的用户昵称</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "head",
            "description": "<p>评论的用户头像URL</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "content",
            "description": "<p>评论内容</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "dateTime",
            "description": "<p>评论时间</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "isOrNotLikes",
            "description": "<p>当前用户是否点赞，0-&gt;点赞，1-&gt;未点赞</p>"
          },
          {
            "group": "200",
            "type": "int",
            "optional": true,
            "field": "likes",
            "description": "<p>当前评论点赞数</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"分页查询某条动态内容的所有评论详情数据成功。\",\n\"data\": {\n\"totalSize\": 15,\n\"totalPage\": 1,\n\"list\": [\n{\n\"userId\": 35,\n\"commentId\": 30,\n\"nickname\": \"思思\",\n\"head\": \"http://127.0.0.1:9000/find/img/head/35/02.png\",\n\"content\": \"好的，会的\",\n\"dateTime\": \"2021-07-21 12:04:39\",\n\"isOrNotLikes\": \"0\",\n\"likes\": 0\n},\n{\n\"userId\": 70,\n\"commentId\": 16,\n\"nickname\": \"阿萌\",\n\"head\": \"http://127.0.0.1:9000/find/img/head/70/03.png\",\n\"content\": \"我的加油，加油。\",\n\"dateTime\": \"2021-07-14 15:31:56\",\n\"isOrNotLikes\": \"0\",\n\"likes\": 3\n},\n{\n\"userId\": 70,\n\"commentId\": 15,\n\"nickname\": \"阿萌\",\n\"head\": \"http://127.0.0.1:9000/find/img/head/70/03.png\",\n\"content\": \"我的加油。\",\n\"dateTime\": \"2021-07-14 15:17:51\",\n\"isOrNotLikes\": \"0\",\n\"likes\": 0\n},\n{\n\"userId\": 122,\n\"commentId\": 14,\n\"nickname\": \"丸子\",\n\"head\": \"http://127.0.0.1:9000/find/img/head/122/01.png\",\n\"content\": \"好的，\",\n\"dateTime\": \"2021-07-13 21:04:58\",\n\"isOrNotLikes\": \"0\",\n\"likes\": 0\n},\n{\n\"userId\": 121,\n\"commentId\": 13,\n\"nickname\": \"夹心小憨宝\",\n\"head\": \"http://127.0.0.1:9000/find/img/head/121/03.png\",\n\"content\": \"好的，\",\n\"dateTime\": \"2021-07-13 21:04:54\",\n\"isOrNotLikes\": \"0\",\n\"likes\": 0\n},\n{\n\"userId\": 138,\n\"commentId\": 12,\n\"nickname\": \"阿妩\",\n\"head\": \"http://127.0.0.1:9000/find/img/head/138/31b0b00e-f8c3-4e23-ba77-d7e50eafe17e.jpg\",\n\"content\": \"好的，\",\n\"dateTime\": \"2021-07-13 21:04:48\",\n\"isOrNotLikes\": \"0\",\n\"likes\": 0\n},\n{\n\"userId\": 141,\n\"commentId\": 11,\n\"nickname\": \"good\",\n\"head\": \"http://127.0.0.1:9000/find/img/head/141/cd118c01-db49-43f9-a857-07bf53ee2918.png\",\n\"content\": \"好的，\",\n\"dateTime\": \"2021-07-13 21:04:43\",\n\"isOrNotLikes\": \"0\",\n\"likes\": 0\n},\n{\n\"userId\": 23,\n\"commentId\": 10,\n\"nickname\": \"北柠陌寒\",\n\"head\": \"http://127.0.0.1:9000/find/img/head/23/06.png\",\n\"content\": \"好的，\",\n\"dateTime\": \"2021-07-13 21:04:36\",\n\"isOrNotLikes\": \"0\",\n\"likes\": 0\n},\n{\n\"userId\": 42,\n\"commentId\": 9,\n\"nickname\": \"轻吟\",\n\"head\": \"http://127.0.0.1:9000/find/img/head/42/01.png\",\n\"content\": \"好的，\",\n\"dateTime\": \"2021-07-13 21:04:31\",\n\"isOrNotLikes\": \"0\",\n\"likes\": 0\n},\n{\n\"userId\": 41,\n\"commentId\": 8,\n\"nickname\": \"卖萌迪\",\n\"head\": \"http://127.0.0.1:9000/find/img/head/41/02.png\",\n\"content\": \"好的，\",\n\"dateTime\": \"2021-07-13 21:04:26\",\n\"isOrNotLikes\": \"0\",\n\"likes\": 0\n},\n{\n\"userId\": 51,\n\"commentId\": 7,\n\"nickname\": \"暮夏\",\n\"head\": \"http://127.0.0.1:9000/find/img/head/51/02.png\",\n\"content\": \"好的，\",\n\"dateTime\": \"2021-07-13 21:04:20\",\n\"isOrNotLikes\": \"0\",\n\"likes\": 0\n},\n{\n\"userId\": 50,\n\"commentId\": 6,\n\"nickname\": \"来愿\",\n\"head\": \"http://127.0.0.1:9000/find/img/head/50/02.png\",\n\"content\": \"好的，我会的，欢迎！\",\n\"dateTime\": \"2021-07-13 20:58:59\",\n\"isOrNotLikes\": \"0\",\n\"likes\": 0\n},\n{\n\"userId\": 60,\n\"commentId\": 5,\n\"nickname\": \"尘埃\",\n\"head\": \"http://127.0.0.1:9000/find/img/head/60/01.png\",\n\"content\": \"好的，我会的，欢迎！\",\n\"dateTime\": \"2021-07-13 20:58:52\",\n\"isOrNotLikes\": \"0\",\n\"likes\": 0\n},\n{\n\"userId\": 63,\n\"commentId\": 4,\n\"nickname\": \"浮生\",\n\"head\": \"http://127.0.0.1:9000/find/img/head/63/07.png\",\n\"content\": \"好的，我会的，欢迎！\",\n\"dateTime\": \"2021-07-13 20:58:30\",\n\"isOrNotLikes\": \"0\",\n\"likes\": 0\n},\n{\n\"userId\": 73,\n\"commentId\": 3,\n\"nickname\": \"如风\",\n\"head\": \"http://127.0.0.1:9000/find/img/head/73/05.png\",\n\"content\": \"好的，我会的，欢迎！\",\n\"dateTime\": \"2021-07-13 20:58:22\",\n\"isOrNotLikes\": \"0\",\n\"likes\": 0\n}\n]\n}\n}",
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
          "content": "HTTP/1.1 403 403响应\n{\n\"status\": 403,\n\"code\": 199,\n\"msg\": \"未找到用户信息\",\n}",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500 错误",
          "content": "{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/CommentController.java",
    "groupTitle": "动态模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/comment/{id}/query"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://127.0.0.1:8084/find/dynamic/{id}/delete",
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
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://127.0.0.1:8084/find/dynamic/70/delete?dynamicInfoId=85",
          "type": "json"
        },
        {
          "title": "请求示例02（非自己发布的动态内容， 删除失败）",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://127.0.0.1:8084/find/dynamic/70/delete?dynamicInfoId=86",
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
        "url": "http://127.0.0.1:8084/find/dynamic/{id}/delete"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://127.0.0.1:8084/find/dynamic/{id}/release",
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
          "content": "HTTP/1.1 OK 注：form表单提交，需要在请求头加：“Content-Type=multipart/form-data;charset=utf-8”\ncurl -v -X POST -H 'multipart/form-data;charset=utf-8' http://127.0.0.1:8084/find/dynamic/3/release\n-d '{\n\"imei\": \"895568564457954422\",\n\"attacheInfoDataType\": \"1\",\n\"files\": \"C:\\Users\\Administrator\\Pictures\\images\\01.jpg,\nC:\\Users\\Administrator\\Pictures\\images\\02.jpg,\nC:\\Users\\Administrator\\Pictures\\images\\03.jpg,\nC:\\Users\\Administrator\\Pictures\\images\\04.jpg\",\n\"model\": \"vivo x7 plus\",\n\"sysName\": \"Android\",\n\"sysCode\": \"9.0\",\n\"networkMode\": \"WIFI\",\n\"country\": \"中国\",\n\"province\": \"广东\",\n\"city\": \"广州\",\n\"publicStatus\": \"0\",\n\"content\": \"发布照片。\"\n}'",
          "type": "json"
        },
        {
          "title": "请求示例02（发布图片有客户端IP）",
          "content": "HTTP/1.1 OK 注：form表单提交，需要在请求头加：“Content-Type=multipart/form-data;charset=utf-8”\ncurl -v -X POST -H 'multipart/form-data;charset=utf-8' http://127.0.0.1:8084/find/dynamic/3/release\n-d '{\n\"imei\": \"895568564457954422\",\n\"attacheInfoDataType\": \"1\",\n\"files\": \"C:\\Users\\Administrator\\Pictures\\images\\01.jpg,\nC:\\Users\\Administrator\\Pictures\\images\\02.jpg,\nC:\\Users\\Administrator\\Pictures\\images\\03.jpg,\nC:\\Users\\Administrator\\Pictures\\images\\04.jpg\",\n\"model\": \"vivo x7 plus\",\n\"sysName\": \"Android\",\n\"sysCode\": \"9.0\",\n\"networkMode\": \"WIFI\",\n\"ip\": \"183.14.31.54\",\n\"publicStatus\": \"0\",\n\"content\": \"发布照片。\"\n}'",
          "type": "json"
        },
        {
          "title": "请求示例03（发布语音有客户端IP）",
          "content": "HTTP/1.1 OK 注：form表单提交，需要在请求头加：“Content-Type=multipart/form-data;charset=utf-8”\ncurl -v -X POST -H 'multipart/form-data;charset=utf-8' http://127.0.0.1:8084/find/dynamic/3/release\n-d '{\n\"imei\": \"895568564457954422\",\n\"attacheInfoDataType\": \"2\",\n\"files\": \"F:\\文件\\各种音乐\\(DJ)中文DJ\\7姨、高梦瑶、妖姬 - 威震八方.mp3\",\n\"model\": \"vivo x7 plus\",\n\"sysName\": \"Android\",\n\"sysCode\": \"9.0\",\n\"networkMode\": \"WIFI\",\n\"ip\": \"183.14.31.54\",\n\"publicStatus\": \"0\",\n\"content\": \"发布语音。\"\n}'",
          "type": "json"
        },
        {
          "title": "请求示例04（发布纯文字有客户端IP）",
          "content": "HTTP/1.1 OK 注：form表单提交，需要在请求头加：“Content-Type=multipart/form-data;charset=utf-8”\ncurl -v -X POST -H 'multipart/form-data;charset=utf-8' http://127.0.0.1:8084/find/dynamic/3/release\n-d '{\n\"imei\": \"895568564457954422\",\n\"attacheInfoDataType\": \"0\",\n\"model\": \"vivo x7 plus\",\n\"sysName\": \"Android\",\n\"sysCode\": \"9.0\",\n\"networkMode\": \"WIFI\",\n\"ip\": \"183.14.31.54\",\n\"publicStatus\": \"0\",\n\"content\": \"发布语音。\"\n}'",
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
        "url": "http://127.0.0.1:8084/find/dynamic/{id}/release"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://127.0.0.1:8084/find/comment/{id}/release",
    "title": "发表评论接口",
    "version": "1.0.0",
    "group": "动态模块API",
    "name": "发表评论",
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
            "optional": false,
            "field": "content",
            "description": "<p>评论内容</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例",
          "content": "HTTP/1.1 OK\ncurl -v -X POST http://127.0.0.1:8084/find/user/70/uploadRegId?regId=1507bfd3f76139cd43a",
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
            "field": "RELEASE",
            "description": "<p>发布状态，OK-&gt;“成功”，FAILED-&gt;“失败”</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"发布评论成功\",\n\"data\": {\n\"RELEASE\": \"OK\"\n}\n}",
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
          "content": "HTTP/1.1 403 403响应\n{\n\"status\": 403,\n\"code\": 199,\n\"msg\": \"未找到用户信息\",\n}",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500 错误",
          "content": "{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/CommentController.java",
    "groupTitle": "动态模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/comment/{id}/release"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://127.0.0.1:8084/find/dynamic/{id}/updateLocation",
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
          "content": "HTTP/1.1 OK\ncurl -v -X POST -H 'application/json;charset=utf-8' http://127.0.0.1:8084/find/dynamic/1/updateLocation -d '{\"ip\":\"183.14.133.239\"}'",
          "type": "json"
        },
        {
          "title": "请求示例02（有定位（国家）、（省份）、（城市））",
          "content": "HTTP/1.1 OK\ncurl -v -X POST -H 'application/json;charset=utf-8' http://127.0.0.1:8084/find/dynamic/1/updateLocation -d '{\"country\": \"中国\", \"province\": \"广东\", \"city\": \"深圳\"}'",
          "type": "json"
        },
        {
          "title": "请求示例03（有客户端IP，定位（国家）、（省份）、（城市））",
          "content": "HTTP/1.1 OK\ncurl -v -X POST -H 'application/json;charset=utf-8' http://127.0.0.1:8084/find/dynamic/1/updateLocation\n-d '{\n\"ip\": \"183.14.133.239\",\n\"country\": \"中国\",\n\"province\": \"广东\",\n\"city\": \"深圳\"\n}'",
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
          "content": "HTTP/1.1 200 OK\ncurl -v -X POST -H 'application/json;charset=utf-8' http://127.0.0.1:8084/find/dynamic/1/updateLocation",
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
        "url": "http://127.0.0.1:8084/find/dynamic/{id}/updateLocation"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://127.0.0.1:8084/find/dynamic/{id}/checkLocation",
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
          "content": "HTTP/1.1 OK\ncurl -v -X POST -H 'application/json;charset=utf-8' http://127.0.0.1:8084/find/dynamic/1/checkLocation -d '{\"ip\":\"183.14.133.239\"}'",
          "type": "json"
        },
        {
          "title": "请求示例02（有发布定位地址（国）、省、市）",
          "content": "HTTP/1.1 OK\ncurl -v -X POST -H 'application/json;charset=utf-8' http://127.0.0.1:8084/find/dynamic/1/checkLocation -d '{\"country\": \"中国\", \"province\": \"广东\", \"city\": \"深圳\"}'",
          "type": "json"
        },
        {
          "title": "请求示例03（有客户端IP，发布定位地址（国）、（省）、（市））",
          "content": "HTTP/1.1 OK\ncurl -v -X POST -H 'application/json;charset=utf-8' http://127.0.0.1:8084/find/dynamic/1/checkLocation\n-d '{\n\"ip\": \"183.14.133.239\",\n\"country\": \"中国\",\n\"province\": \"广东\",\n\"city\": \"深圳\"\n}'",
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
          "content": "HTTP/1.1 200 OK\ncurl -v -X POST -H 'application/json;charset=utf-8' http://127.0.0.1:8084/find/dynamic/1/checkLocation -d '{}'",
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
        "url": "http://127.0.0.1:8084/find/dynamic/{id}/checkLocation"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://127.0.0.1:8084/find/comment/{id}/likes",
    "title": "点赞/取消评论接口",
    "version": "1.0.0",
    "group": "动态模块API",
    "name": "点赞/取消评论接口",
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
            "field": "commentId",
            "description": "<p>评论id</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": false,
            "field": "type",
            "description": "<p>类型，0-&gt;取消，1-&gt;点赞</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例（点赞）",
          "content": "curl -v -X PUT http://127.0.0.1:8084/find/comment/35/likes?commentId=16&type=1",
          "type": "json"
        },
        {
          "title": "请求示例（取消）",
          "content": "curl -v -X PUT http://127.0.0.1:8084/find/comment/35/likes?commentId=16&type=0",
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
            "field": "LIKES",
            "description": "<p>点赞状态，OK-&gt;“成功”，FAILED-&gt;“失败”</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例（点赞）",
          "content": "{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"点赞成功。\",\n\"data\": {\n\"LIKES\": \"OK\"\n}\n}",
          "type": "json"
        },
        {
          "title": "200响应示例（取消）",
          "content": "{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"取消点赞成功。\",\n\"data\": {\n\"LIKES\": \"OK\"\n}\n}",
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
          "content": "HTTP/1.1 403 403响应\n{\n\"status\": 403,\n\"code\": 199,\n\"msg\": \"未找到用户信息\",\n}",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500 错误",
          "content": "{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/CommentController.java",
    "groupTitle": "动态模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/comment/{id}/likes"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://127.0.0.1:8084/find/dynamic/{id}/likes",
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
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://127.0.0.1:8084/find/dynamic/70/likes?dynamicInfoId=86&type=0",
          "type": "json"
        },
        {
          "title": "请求示例02（取消点赞，点赞记录存在）",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://127.0.0.1:8084/find/dynamic/70/likes?dynamicInfoId=86&type=0",
          "type": "json"
        },
        {
          "title": "请求示例03（点赞，点赞记录不存在）",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://127.0.0.1:8084/find/dynamic/70/likes?dynamicInfoId=86&type=1",
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
        "url": "http://127.0.0.1:8084/find/dynamic/{id}/likes"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://127.0.0.1:8084/find/dynamic/{id}/application",
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
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://127.0.0.1:8084/find/dynamic/70/application?dynamicInfoId=86&message=需要加您的微信，请发送微信号码过来",
          "type": "json"
        },
        {
          "title": "请求示例02（第6次申请加微信）",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://127.0.0.1:8084/find/dynamic/70/application?dynamicInfoId=86&message=需要加您的微信16",
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
        "url": "http://127.0.0.1:8084/find/dynamic/{id}/application"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://127.0.0.1:8084/find/dynamic/{id}/screen",
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
          "content": "HTTP/1.1 OK\ncurl -v -X GET http://127.0.0.1:8084/find/dynamic/71/screen?pageNum=1&pageSize=20&gender=0&minAge=16&maxAge=39&constellation=巨蟹座,水瓶座&dataType=0&provinceList=广东省,广西省,湖南省&cityList=深圳市,广州市,南宁市,长沙市",
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
        "url": "http://127.0.0.1:8084/find/dynamic/{id}/screen"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://127.0.0.1:8084/find/dynamic/{id}/mylist",
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
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://127.0.0.1:8084/find/dynamic/71/mylist?pageNum=1&pageSize=20",
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
        "url": "http://127.0.0.1:8084/find/dynamic/{id}/mylist"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://127.0.0.1:8084/find/dynamic/{id}/list",
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
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://127.0.0.1:8084/find/dynamic/73/list?pageNum=1&pageSize=20",
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
        "url": "http://127.0.0.1:8084/find/dynamic/{id}/list"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://127.0.0.1:8084/find/message/{id}/updateAll",
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
          "content": "HTTP/1.1 OK\ncurl -v -X PUT \"http://127.0.0.1:8084/find/message/60/updateAll\" -H \"accept: application/json\"",
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
        "url": "http://127.0.0.1:8084/find/message/{id}/updateAll"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://127.0.0.1:8084/find/message/{id1}/{id2}/messages",
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
          "content": "HTTP/1.1 OK\ncurl -v -X GET \"http://127.0.0.1:8084/find/message/138/139/messages?pageNum=1&pageSize=20\" -H \"accept: application/json\"",
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
        "url": "http://127.0.0.1:8084/find/message/{id1}/{id2}/messages"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://127.0.0.1:8084/find/message/{id}/all",
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
          "content": "HTTP/1.1 OK\ncurl -v -X GET \"http://127.0.0.1:8084/find/message/29/all?pageNum=1&pageSize=20\" -H \"accept: application/json\"",
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
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "createTime",
            "description": "<p>消息发送时间</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "type",
            "description": "<p>消息类型，0-&gt;普通消息，1-&gt;申请加微信消息</p>"
          },
          {
            "group": "200",
            "type": "int",
            "optional": true,
            "field": "flag",
            "description": "<p>是否展示复制微信，0-&gt;否，1-&gt;是</p>"
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
          "content": "HTTP/1.1 200 OK\n{\n\"status\":200,\n\"code\":0,\n\"msg\":\"返回数据成功\",\n\"data\":{\n\"likes\":{\n\"content1\":\"阿萌赞了你的动态差点就掉下去了！\",\n\"count1\":5\n},\n\"totalCount\":5,\n\"totalPage\":1,\n\"list\":[\n{\n\"messageId\": 7,\n\"userId\":60,\n\"head\":\"http://8.135.36.45:8000/find/img/head/60/01.png\",\n\"nickname\":\"尘埃\",\n\"content2\":\"需要加您的微信?\",\n\"count2\":5,\n\"createTime\": \"2021年04月22日 16:40:40\",\n\"type\":1,\n\"flag\":0\n},\n{\n\"messageId\": 2,\n\"userId\":62,\n\"head\":\"http://8.135.36.45:8000/find/img/head/62/02.png\",\n\"nickname\":\"蓝梧桐\",\n\"content2\":\"需要加您的微信?\",\n\"count2\":5,\n\"createTime\": \"2021年04月22日 16:40:40\",\n\"type\":1,\n\"flag\":0\n},\n{\n\"messageId\": 3,\n\"userId\":61,\n\"head\":\"http://8.135.36.45:8000/find/img/head/61/01.png\",\n\"nickname\":\"长安\",\n\"content2\":\"需要加您的微信?\",\n\"count2\":6\n\"createTime\": \"2021年04月22日 16:40:40\",\n\"type\":1,\n\"flag\":0\n},\n{\n\"messageId\": 4,\n\"userId\":71,\n\"head\":\"http://8.135.36.45:8000/find/img/head/71/07.png\",\n\"nickname\":\"弦雨晴\",\n\"content2\":\"需要加您的微信?\",\n\"count2\":6\n\"createTime\": \"2021年04月22日 16:40:40\",\n\"type\":1,\n\"flag\":0\n},\n{\n\"messageId\": 5,\n\"userId\":70,\n\"head\":\"http://8.135.36.45:8000/find/img/head/70/03.png\",\n\"nickname\":\"阿萌\",\n\"content2\":\"需要加您的微信?\",\n\"createTime\": \"2021年04月22日 16:40:40\",\n\"count2\":1\n\"type\":1,\n\"flag\":0\n},\n{\n\"messageId\": 86,\n\"userId\": 137,\n\"head\": \"http://8.135.36.45:8000/find/img/head/137/34ca77aa-b3e2-4358-b7cf-0acb172121db.jpeg\",\n\"nickname\": \"jack\",\n\"content2\": \"已同意添加微信，我的微信号是：\",\n\"createTime\": \"2021年04月22日 16:40:40\",\n\"count2\": 2,\n\"type\": \"1\",\n\"flag\": 1,\n\"weixinId\": \"wxnaza12345681\"\n}\n]\n}\n}",
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
        "url": "http://127.0.0.1:8084/find/message/{id}/all"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://127.0.0.1:8084/find/message/{id}/likes",
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
          "content": "HTTP/1.1 OK\ncurl -v -X GET \"http://127.0.0.1:8084/find/message/29/likes?pageNum=1&pageSize=20\" -H \"accept: application/json\"",
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
        "url": "http://127.0.0.1:8084/find/message/{id}/likes"
      }
    ]
  },
  {
    "type": "delete",
    "url": "http://127.0.0.1:8084/find/message/{id}/deleteLikes",
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
          "content": "HTTP/1.1 OK\ncurl -v -X PUT \"http://127.0.0.1:8084/find/message/60/deleteLikes?messageId=28\" -H \"accept: application/json\"",
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
        "url": "http://127.0.0.1:8084/find/message/{id}/deleteLikes"
      }
    ]
  },
  {
    "type": "delete",
    "url": "http://127.0.0.1:8084/find/message/{id1}/delete",
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
          "content": "HTTP/1.1 OK\ncurl -v -X PUT \"http://127.0.0.1:8084/find/message/60/delete?id2=28\" -H \"accept: application/json\"",
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
        "url": "http://127.0.0.1:8084/find/message/{id1}/delete"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://127.0.0.1:8084/find/message/{id}/send",
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
            "field": "id",
            "description": "<p>发送者用户id</p>"
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
            "optional": false,
            "field": "content",
            "description": "<p>消息内容</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例 发送消息",
          "content": "HTTP/1.1 OK\ncurl -v -X POST \"http://127.0.0.1:8084/find/message/60/send?messageId=25&content=可以申请加你的微信吗？\" -H \"accept: application/json\"",
          "type": "json"
        },
        {
          "title": "请求示例 回复消息",
          "content": "HTTP/1.1 OK\ncurl -v -X POST \"http://127.0.0.1:8084/find/message/29/send?messageId=2&content=可以申请加你的微信吗？\" -H \"accept: application/json\"",
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
        "url": "http://127.0.0.1:8084/find/message/{id}/send"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://127.0.0.1:8084/find/message/{id}/reply",
    "title": "回复申请加微信消息记录接口",
    "version": "1.0.0",
    "group": "消息模块API",
    "name": "回复申请加微信消息记录",
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
            "field": "messageId",
            "description": "<p>消息id</p>"
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
            "description": "<p>类型，0-&gt;拒绝，1-&gt;同意</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "content",
            "description": "<p>消息内容</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "weChatId",
            "description": "<p>微信号</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例 回复申请加微信消息（拒绝）",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT \"http://127.0.0.1:8084/find/message/138/reply?messageId=37&type=0&content=非常抱歉，我不想加你！\" -H \"accept: application/json\"",
          "type": "json"
        },
        {
          "title": "请求示例 回复申请加微信消息（同意）",
          "content": "HTTP/1.1 OK\ncurl -v -X PUT \"http://127.0.0.1:8084/find/144/reply?messageId=42&type=1&content=我乐意&weChatId=wx406151651a\" -H \"accept: application/json\"",
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
        "url": "http://127.0.0.1:8084/find/message/{id}/reply"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://127.0.0.1:8084/find/user/{id}/uploadRegId",
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
          "content": "HTTP/1.1 OK\ncurl -v -X PUT http://127.0.0.1:8084/find/user/70/uploadRegId?regId=1507bfd3f76139cd43a",
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
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"返回数据成功\",\n\"data\": {\n\"UPLOADREGID\": \"OK\",\n}\n}",
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
          "content": "HTTP/1.1 403 403响应\n{\n\"status\": 403,\n\"code\": 199,\n\"msg\": \"未找到用户信息\",\n}",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500 错误",
          "content": "{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/user/{id}/uploadRegId"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://127.0.0.1:8084/find/user/{id}/head",
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
          "content": "HTTP/1.1 OK\n{\n\"headIconFile\":\"D:\\Program\\Resources\\find\\img\\head\\head01.png\"\n}",
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
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"返回数据成功\",\n\"data\": {\n\"head\": \"http://8.135.36.45:8000/find/img/head/2b9c022d-ec00-497c-9626-813add17b877_admin069.jpg\",\n\"id\": 1\n}\n}",
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
          "content": "HTTP/1.1 403 403响应\n{\n\"status\": 403,\n\"code\": 199,\n\"msg\": \"未找到用户信息\",\n}",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500 错误",
          "content": "{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/user/{id}/head"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://127.0.0.1:8084/find/user/{id}/background",
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
          "content": "HTTP/1.1 OK\n{\n\"backgroundIconFile\":\"D:\\Program\\Resources\\find\\img\\head\\bg02.png\"\n}",
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
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"返回数据成功\",\n\"data\": {\n\"bg\": \"http://8.135.36.45:8000/find/img/head/2b9c022d-ec00-497c-9626-813add17b877_admin069.jpg\",\n\"id\": 1\n}\n}",
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
          "content": "HTTP/1.1 403 403响应\n{\n\"status\": 403,\n\"code\": 199,\n\"msg\": \"未找到用户信息\",\n}",
          "type": "json"
        },
        {
          "title": "404错误",
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500 错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/user/{id}/background"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://127.0.0.1:8084/find/user/isreg?phone={手机号码}",
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
          "content": "HTTP/1.1 OK\ncurl -v -X GET http://127.0.0.1:8084/find/user/isreg?phone=18138802541",
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
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"SUCCESS，还未注册。\",\n\"data\": {\n\"isReg\": false\n}\n}",
          "type": "json"
        },
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"SUCCESS，已经注册。\",\n\"data\": {\n\"isReg\": true\n}\n}",
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
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/user/isreg?phone={手机号码}"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://127.0.0.1:8084/find/user/{id}/pushOrPull",
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
          "content": "HTTP/1.1 OK\ncurl --insecure -X POST -v http://127.0.0.1:8084/find/user/1/pushOrPull -H \"Content-Type: application/json;charset=UTF-8\" -d '{\"blackUserId\":2, \"type\":0}'",
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
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"将用户千柳推出黑名单列表成功。\",\n\"data\": {\n\"PULL\": \"OK\"\n}\n}",
          "type": "json"
        },
        {
          "title": "200响应示例 拉入黑名单列表",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"将用户千柳拉入黑名单列表成功。\",\n\"data\": {\n\"PUSH\": \"OK\"\n}\n}",
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
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/user/{id}/pushOrPull"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://127.0.0.1:8084/find/user/{id}/update",
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
          },
          {
            "group": "接口请求参数",
            "type": "long",
            "optional": true,
            "field": "professionId",
            "description": "<p>职业编号</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "tag1",
            "description": "<p>标签1</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "tag2",
            "description": "<p>标签2</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "tag3",
            "description": "<p>标签3</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "tag4",
            "description": "<p>标签4</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": true,
            "field": "tag5",
            "description": "<p>标签5</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例 修改昵称",
          "content": "HTTP/1.1 OK\ncurl --insecure -X PUT -v http://127.0.0.1:8084/find/user/{id}/update -H \"Content-Type: application/json;;charset=UTF-8\" -d '{\"nickname\":\"王6\"}'",
          "type": "json"
        },
        {
          "title": "请求示例 修改微信号",
          "content": "HTTP/1.1 OK\ncurl --insecure -X PUT -v http://127.0.0.1:8084/find/user/{id}/update -H \"Content-Type: application/json;charset=UTF-8\" -d '{\"weixinId\":\"12622\"}'",
          "type": "json"
        },
        {
          "title": "请求示例 修改出生年月日和星座",
          "content": "HTTP/1.1 OK\ncurl --insecure -X PUT -v http://127.0.0.1:8084/find/user/{id}/update -H \"Content-Type: application/json;charset=UTF-8\" -d '{\"year\":\"1996\", \"month\":\"08\", \"date\":\"03\", \"constellation\":\"双子座\"}'",
          "type": "json"
        },
        {
          "title": "请求示例 修改签名",
          "content": "HTTP/1.1 OK\ncurl --insecure -X PUT -v http://127.0.0.1:8084/find/user/{id}/update -H \"Content-Type: application/json;charset=UTF-8\" -d '{\"autograph\":\"12622\"}'",
          "type": "json"
        },
        {
          "title": "请求示例 修改签名和出生年月日，星座",
          "content": "HTTP/1.1 OK\ncurl --insecure -X PUT -v http://127.0.0.1:8084/find/user/{id}/update -H \"Content-Type: application/json;charset=UTF-8\" -d '{\"autograph\":\"126我的2\", \"year\":\"1996\", \"month\":\"08\", \"date\":\"03\", \"constellation\":\"水平座\"}'",
          "type": "json"
        },
        {
          "title": "请求示例 修改签名，出生年月日，星座，昵称，微信号",
          "content": "HTTP/1.1 OK\ncurl --insecure -X PUT -v http://127.0.0.1:8084/find/user/{id}/update -H \"Content-Type: application/json;charset=UTF-8\" -d '{\"nickname\":\"王666\", \"weixinId\":\"12622www\", \"autograph\":\"126我的ss2\", \"year\":\"1996\", \"month\":\"08\", \"date\":\"03\", \"constellation\":\"射手座\"}'",
          "type": "json"
        },
        {
          "title": "请求示例 修改职业",
          "content": "HTTP/1.1 OK\ncurl --insecure -X PUT -v http://127.0.0.1:8084/find/user/{id}/update -H \"Content-Type: application/json;charset=UTF-8\" -d '{\"professionId\": 20}'",
          "type": "json"
        },
        {
          "title": "请求示例 修改标签1，标签2，标签3",
          "content": "HTTP/1.1 OK\ncurl --insecure -X PUT -v http://127.0.0.1:8084/find/user/{id}/update -H \"Content-Type: application/json;charset=UTF-8\" -d '\n{\n\"tag1\": \"颜值\",\n\"tag2\": \"音乐\",\n\"tag3\": \"影视\"\n}'",
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
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"修改或者更新用户资料成功\",\n\"data\": {\n\"UPDATE\": \"OK\"\n}\n}",
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
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/user/{id}/update"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://127.0.0.1:8084/find/user/{id}/queryWeixin",
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
          "content": "HTTP/1.1 OK\ncurl --insecure -X GET -v http://127.0.0.1:8084/find/user/1/queryWeixin",
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
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"查看用户个人资料成功\",\n\"data\": {\n\"user\": {\n\"weixinId\": 11111111\n}\n}\n}",
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
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/user/{id}/queryWeixin"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://127.0.0.1:8084/find/user/{id}/query",
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
          "content": "HTTP/1.1 OK\ncurl --insecure -X GET -v http://127.0.0.1:8084/find/user/1/query",
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
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"查看用户个人资料成功\",\n\"data\": {\n\"user\": {\n\"id\": 1,\n\"nickname\": \"王666\",\n\"head\": \"http://8.135.36.45:8000/find/img/head/2b9c022d-ec00-497c-9626-813add17b877_admin069.jpg\",\n\"bg\": \"http://8.135.36.45:8000/find/img/background/a1bf6181-ebd0-43b4-8e91-761ec8fc83ab_admin055.jpg\",\n\"grade\": 0,\n\"age\": 24,\n\"gender\": \"1\",\n\"autograph\": \"126我的ss2\"\n}\n}\n}",
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
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/user/{id}/query"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://127.0.0.1:8084/find/user/search-tag",
    "title": "模糊搜索标签接口",
    "version": "1.0.0",
    "group": "用户模块API",
    "name": "模糊搜索标签",
    "parameter": {
      "fields": {
        "接口请求参数": [
          {
            "group": "接口请求参数",
            "type": "string",
            "optional": false,
            "field": "keywords",
            "description": "<p>关键词</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例",
          "content": "HTTP/1.1 OK\ncurl --insecure -X GET -v http://127.0.0.1:8084/find/user/search-tag?keywords=元 -H \"Content-Type: application/json;charset=UTF-8\"",
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
            "description": "<p>标签列表</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "id",
            "description": "<p>标签id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "name",
            "description": "<p>标签名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "HTTP/1.1 200 OK",
          "content": "{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"模糊搜索标签列表成功\",\n\"data\": {\n\"list\": [\n{\n\"id\": 5,\n\"name\": \"二次元\"\n}\n]\n}\n}",
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
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/user/search-tag"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://127.0.0.1:8084/find/user/{id}/report",
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
          "content": "HTTP/1.1 OK\ncurl --insecure -X POST -v http://127.0.0.1:8084/find/user/{id}/report -H \"Content-Type: application/json;charset=UTF-8\" -d '{\"categoryId\":1, \"reportType\":1, \"beingReportId\":3, \"reportContent\":\"老是打广告dddddd+++++++！！！！！\"}'",
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
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"记录用户举报内容成功。\",\n\"data\": {\n\"REPORTED\": \"OK\"\n}\n}",
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
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/user/{id}/report"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://127.0.0.1:8084/find/user/reg",
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
            "size": "16",
            "optional": true,
            "field": "ip",
            "description": "<p>客户端IP不能与定位（国家、省份，城市、区/县、其它、经度、纬度）同时为空</p>"
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
            "size": "0..32",
            "optional": true,
            "field": "district",
            "description": "<p>定位（区/县）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..32",
            "optional": true,
            "field": "other",
            "description": "<p>定位（其它）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "double",
            "size": "0..16",
            "optional": true,
            "field": "longitude",
            "description": "<p>定位（经度）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "double",
            "size": "0..16",
            "optional": true,
            "field": "latitude",
            "description": "<p>定位（纬度）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "long",
            "size": "0..32",
            "optional": true,
            "field": "professionId",
            "description": "<p>职业编号</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..32",
            "optional": true,
            "field": "tag1",
            "description": "<p>标签1</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..32",
            "optional": true,
            "field": "tag2",
            "description": "<p>标签2</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..32",
            "optional": true,
            "field": "tag3",
            "description": "<p>标签3</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..32",
            "optional": true,
            "field": "tag4",
            "description": "<p>标签4</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..32",
            "optional": true,
            "field": "tag5",
            "description": "<p>标签5</p>"
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
          "content": "HTTP/1.1 OK 封装表单数据格式01 注：form表单提交，需要在请求头加：“Content-Type=multipart/form-data;charset=utf-8”\n\"phone\" : \"18138812110\",\n\"gender\" : \"1\",\n\"platform\" : \"Baidu\",\n\"nickname\" : \"张三\",\n\"weixinId\" : \"wx123456788\",\n\"imei\" : \"123456788222\",\n\"model\" : \"vivo Y55\",\n\"sysName\" : \"Android\",\n\"sysCode\" : \"6.0.1\",\n\"networkMode\" : \"WIFI\",\n\"year\" : \"1995\",\n\"month\" : \"05\",\n\"date\" : \"05\",\n\"constellation\" : \"巨蟹座\",\n\"ip\" : \"183.14.29.70\",\n\"professionId\" : 15,\n\"tag1\" : \"音乐\",\n\"tag2\" : \"篮球\",\n\"autograph\" : \"新人来到，多多关照，谢谢！\",\n\"head\":\"D:\\Program\\Resources\\find\\img\\head\\02.png\"",
          "type": "json"
        },
        {
          "title": "请求示例02（注册02）",
          "content": "HTTP/1.1 OK 封装表单数据格式02 注：form表单提交，需要在请求头加：“Content-Type=multipart/form-data;charset=utf-8”\n\"phone\" : \"18138812310\",\n\"gender\" : \"0\",\n\"platform\" : \"HUAWEI\",\n\"nickname\" : \"李四\",\n\"weixinId\" : \"wx123456700\",\n\"imei\" : \"123456788111\",\n\"model\" : \"vivo Z5\",\n\"sysName\" : \"Android\",\n\"sysCode\" : \"9.0.1\",\n\"networkMode\" : \"WIFI\",\n\"year\" : \"1993\",\n\"month\" : \"05\",\n\"date\" : \"05\",\n\"constellation\" : \"巨蟹座\",\n\"professionId\" : 5,\n\"tag1\" : \"学习\",\n\"tag2\" : \"音乐\",\n\"tag3\" : \"足球\",\n\"autograph\" : \"大家好，陌生人报道，多多关照。\",\n\"ip\" : \"123.84.129.170\",\n\"country\" : \"中国\",\n\"province\" : \"广东省\",\n\"city\" : \"深圳市\",\n\"district\" : \"南山区\",\n\"other\" : \"科兴科学园A座\",\n\"longitude\" : 113.862941,\n\"latitude\" : 22.452714,\n\"head\":\"D:\\Program\\Resources\\find\\img\\head\\01.png\"",
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
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"登录或者注册成功。\",\n\"data\": {\n\"user\": {\n\"id\": 21,\n\"gender\": \"1\",\n\"nickname\": \"张三\",\n\"head\": \"http://8.135.36.45:8000/find/img/head/head.png\",\n\"bg\": \"http://8.135.36.45:8000/find/img/background/bg.png\",\n\"autograph\": \"新人小生，初来乍到，请多关照。\"\n}\n}\n}",
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
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/user/reg"
      }
    ]
  },
  {
    "type": "put",
    "url": "http://127.0.0.1:8084/find/user/login",
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
            "description": "<p>客户端IP不能与定位（国家、省份、城市、区/县、其它、经纬度）同时为空</p>"
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
            "size": "0..32",
            "optional": true,
            "field": "district",
            "description": "<p>定位（区/县）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "string",
            "size": "0..32",
            "optional": true,
            "field": "other",
            "description": "<p>定位（其它）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "double",
            "size": "0..16",
            "optional": true,
            "field": "longitude",
            "description": "<p>定位（经度）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "double",
            "size": "0..16",
            "optional": true,
            "field": "latitude",
            "description": "<p>定位（纬度）</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例01（手机号码和客户端IP登录）",
          "content": "HTTP/1.1 OK\ncurl --insecure -X PUT -v http://127.0.0.1:8084/find/user/login?phone=18138812310&ip=183.14.29.70",
          "type": "json"
        },
        {
          "title": "请求示例02（手机号码和定位地址登录）",
          "content": "HTTP/1.1 OK\ncurl --insecure -X PUT -v http://127.0.0.1:8084/find/user/login?phone=18138812236&country=中国&province=广东省&city=广州市&district=荔湾区&other=荔湾汽车站&longitude=103.962941&latitude=21.462714&ip=181.14.30.190",
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
            "field": "autograph",
            "description": "<p>签名</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"登录成功。\",\n\"data\": {\n\"user\": {\n\"id\": 21,\n\"gender\": \"1\",\n\"nickname\": \"张三\",\n\"head\": \"http://8.135.36.45:8000/find/img/head/head.png\",\n\"autograph\": \"新人小生，初来乍到，请多关照。\"\n}\n}\n}",
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
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/user/login"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://127.0.0.1:8084/find/user/hot-tags",
    "title": "获取热门标签列表接口",
    "version": "1.0.0",
    "group": "用户模块API",
    "name": "获取热门标签列表",
    "parameter": {
      "examples": [
        {
          "title": "请求示例",
          "content": "HTTP/1.1 OK\ncurl --insecure -X GET -v http://127.0.0.1:8084/find/user/hot-tags -H \"Content-Type: application/json;charset=UTF-8\"",
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
            "description": "<p>标签列表</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "id",
            "description": "<p>标签id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "name",
            "description": "<p>标签名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "HTTP/1.1 200 OK",
          "content": "{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"获取热门标签列表成功\",\n\"data\": {\n\"list\": [\n{\n\"id\": 1,\n\"name\": \"颜值\"\n},\n{\n\"id\": 2,\n\"name\": \"吃货\"\n},\n{\n\"id\": 3,\n\"name\": \"篮球\"\n},\n{\n\"id\": 4,\n\"name\": \"学习\"\n},\n{\n\"id\": 5,\n\"name\": \"二次元\"\n},\n{\n\"id\": 6,\n\"name\": \"音乐\"\n},\n{\n\"id\": 7,\n\"name\": \"旅游\"\n},\n{\n\"id\": 8,\n\"name\": \"足球\"\n},\n{\n\"id\": 9,\n\"name\": \"游戏\"\n},\n{\n\"id\": 10,\n\"name\": \"影视\"\n}\n]\n}\n}",
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
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/user/hot-tags"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://127.0.0.1:8084/find/user/{id}/report-categories",
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
          "content": "HTTP/1.1 OK\ncurl --insecure -X GET -v http://127.0.0.1:8084/find/user/1/report-categories",
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
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"获取用户举报类型列表成功\",\n\"data\": {\n\"list\": [\n{\n\"id\": 1,\n\"name\": \"垃圾广告\"\n},\n{\n\"id\": 2,\n\"name\": \"色情低俗\"\n},\n{\n\"id\": 3,\n\"name\": \"政治敏感\"\n},\n{\n\"id\": 4,\n\"name\": \"恐怖暴力\"\n},\n{\n\"id\": 5,\n\"name\": \"违法暴力\"\n},\n{\n\"id\": 6,\n\"name\": \"自杀自残\"\n},\n{\n\"id\": 7,\n\"name\": \"侵犯个人信息\"\n},\n{\n\"id\": 8,\n\"name\": \"侵犯未成年人权益\"\n}\n]\n}\n}",
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
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/user/{id}/report-categories"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://127.0.0.1:8084/find/user/tags",
    "title": "获取用户注册标签列表接口",
    "version": "1.0.0",
    "group": "用户模块API",
    "name": "获取用户注册标签列表",
    "parameter": {
      "examples": [
        {
          "title": "请求示例",
          "content": "HTTP/1.1 OK\ncurl --insecure -X GET -v http://127.0.0.1:8084/find/user/tags -H \"Content-Type: application/json;charset=UTF-8\"",
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
            "description": "<p>标签列表</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "id",
            "description": "<p>标签id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "name",
            "description": "<p>标签名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "HTTP/1.1 200 OK",
          "content": "{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"获取用户标签列表成功\",\n\"data\": {\n\"list\": [\n{\n\"id\": 1,\n\"name\": \"颜值\"\n},\n{\n\"id\": 2,\n\"name\": \"吃货\"\n},\n{\n\"id\": 3,\n\"name\": \"篮球\"\n},\n{\n\"id\": 4,\n\"name\": \"学习\"\n},\n{\n\"id\": 5,\n\"name\": \"二次元\"\n},\n{\n\"id\": 6,\n\"name\": \"音乐\"\n},\n{\n\"id\": 7,\n\"name\": \"旅游\"\n},\n{\n\"id\": 8,\n\"name\": \"足球\"\n},\n{\n\"id\": 9,\n\"name\": \"游戏\"\n},\n{\n\"id\": 10,\n\"name\": \"影视\"\n}\n]\n}\n}",
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
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/user/tags"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://127.0.0.1:8084/find/user/{id}/blacklist?pageNum={pageNum}&pageSize={pageSize}",
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
          "content": "HTTP/1.1 OK\ncurl --insecure -X GET -v http://127.0.0.1:8084/find/user/1/blacklist?pageNum=1&pageSize=10",
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
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"获取用户黑名单列表成功。\",\n\"data\": {\n\"totalPage\": 2,\n\"list\": [\n{\n\"id\": 12,\n\"nickname\": \"陆蕙兰\",\n\"head\": \"http://8.135.36.45:8000/find/img/head/12.jpg\",\n\"time\": \"2021年01月22日 15:25:27\"\n},\n{\n\"id\": 11,\n\"nickname\": \"商梓珊\",\n\"head\": \"http://8.135.36.45:8000/find/img/head/11.jpg\",\n\"time\": \"2021年01月22日 15:24:51\"\n},\n{\n\"id\": 10,\n\"nickname\": \"赫飞雨\",\n\"head\": \"http://8.135.36.45:8000/find/img/head/10.jpg\",\n\"time\": \"2021年01月22日 15:24:47\"\n},\n{\n\"id\": 9,\n\"nickname\": \"寒烟\",\n\"head\": \"http://8.135.36.45:8000/find/img/head/09.jpg\",\n\"time\": \"2021年01月22日 15:24:42\"\n},\n{\n\"id\": 7,\n\"nickname\": \"勾高朗\",\n\"head\": \"http://8.135.36.45:8000/find/img/head/07.jpg\",\n\"time\": \"2021年01月22日 15:24:30\"\n},\n{\n\"id\": 6,\n\"nickname\": \"慕蕊\",\n\"head\": \"http://8.135.36.45:8000/find/img/head/06.jpg\",\n\"time\": \"2021年01月22日 15:24:24\"\n},\n{\n\"id\": 5,\n\"nickname\": \"庾音韵\",\n\"head\": \"http://8.135.36.45:8000/find/img/head/05.jpg\",\n\"time\": \"2021年01月22日 15:24:19\"\n},\n{\n\"id\": 4,\n\"nickname\": \"覃星宇\",\n\"head\": \"http://8.135.36.45:8000/find/img/head/04.jpg\",\n\"time\": \"2021年01月22日 15:24:14\"\n},\n{\n\"id\": 3,\n\"nickname\": \"致命德毒药\",\n\"head\": \"http://8.135.36.45:8000/find/img/head/03.jpg\",\n\"time\": \"2021年01月22日 15:24:08\"\n},\n{\n\"id\": 2,\n\"nickname\": \"千柳\",\n\"head\": \"http://8.135.36.45:8000/find/img/head/02.jpg\",\n\"time\": \"2021年01月22日 15:24:02\"\n}\n]\n}\n}",
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
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/user/{id}/blacklist?pageNum={pageNum}&pageSize={pageSize}"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://127.0.0.1:8084/find/user/professions",
    "title": "获取行业和职业列表接口",
    "version": "1.0.0",
    "group": "用户模块API",
    "name": "获取行业和职业列表接口",
    "parameter": {
      "examples": [
        {
          "title": "请求示例",
          "content": "HTTP/1.1 OK\ncurl --insecure -X GET -v http://127.0.0.1:8084/find/user/professions -H \"Content-Type: application/json;charset=UTF-8\"",
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
            "description": "<p>行业列表</p>"
          },
          {
            "group": "200",
            "type": "long",
            "optional": true,
            "field": "id",
            "description": "<p>行业id</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "name",
            "description": "<p>行业名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "200响应示例 行业和职业列表",
          "content": "HTTP/1.1 200 OK\n{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"获取用户注册行业和岗位信息列表成功\",\n\"data\": {\n\"list\": [\n{\n\"id\": 1,\n\"name\": \"计算机/互联网/通信/电子\",\n\"list\": [\n{\n\"id\": 1,\n\"name\": \"后端开发工程师\"\n},\n{\n\"id\": 2,\n\"name\": \"移动开发工程师\"\n},\n{\n\"id\": 3,\n\"name\": \"前端开发工程师\"\n},\n{\n\"id\": 4,\n\"name\": \"算法工程师\"\n},\n{\n\"id\": 5,\n\"name\": \"测试工程师\"\n},\n{\n\"id\": 6,\n\"name\": \"运维/技术支持\"\n},\n{\n\"id\": 7,\n\"name\": \"产品经理\"\n},\n{\n\"id\": 8,\n\"name\": \"运营\"\n},\n{\n\"id\": 9,\n\"name\": \"技术管理\"\n},\n{\n\"id\": 10,\n\"name\": \"电子商务\"\n},\n{\n\"id\": 11,\n\"name\": \"半导体/芯片\"\n},\n{\n\"id\": 12,\n\"name\": \"电子/电器/仪器仪表\"\n},\n{\n\"id\": 13,\n\"name\": \"通信技术开发及应用\"\n},\n{\n\"id\": 14,\n\"name\": \"项目管理\"\n}\n]\n},\n{\n\"id\": 2,\n\"name\": \"金融/银行/保险\",\n\"list\": [\n{\n\"id\": 15,\n\"name\": \"金融/投资/证券\"\n},\n{\n\"id\": 16,\n\"name\": \"银行\"\n},\n{\n\"id\": 17,\n\"name\": \"保险\"\n},\n{\n\"id\": 18,\n\"name\": \"信托/担保/拍卖/典当\"\n}\n]\n}\n]\n}\n}",
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
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/user/professions"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://127.0.0.1:8084/find/user/{id}/look",
    "title": "鹿可模块推荐用户数据接口",
    "version": "1.0.0",
    "group": "用户模块API",
    "name": "鹿可模块推荐用户数据",
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
            "field": "ip",
            "description": "<p>客户端ip</p>"
          },
          {
            "group": "接口请求参数",
            "type": "double",
            "optional": false,
            "field": "longitude",
            "description": "<p>定位（经度）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "double",
            "optional": false,
            "field": "latitude",
            "description": "<p>定位（纬度）</p>"
          },
          {
            "group": "接口请求参数",
            "type": "int",
            "optional": true,
            "field": "count",
            "description": "<p>推荐用户数量，默认：10</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例",
          "content": "HTTP/1.1 OK\ncurl --insecure -X GET -v http://127.0.0.1:8084/find/user/35/look?ip=183.14.135.75&longitude=113.9629412&latitude=22.4627142&count=10 -H \"Content-Type: application/json;charset=UTF-8\"",
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
            "description": "<p>鹿可用户列表</p>"
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
            "description": "<p>用户昵称</p>"
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
            "optional": true,
            "field": "country",
            "description": "<p>国家</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "province",
            "description": "<p>省份</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "city",
            "description": "<p>城市</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "district",
            "description": "<p>区/县</p>"
          },
          {
            "group": "200",
            "type": "double",
            "optional": true,
            "field": "distance",
            "description": "<p>距离（单位：米）</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "img",
            "description": "<p>动态图片地址</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "HTTP/1.1 200 OK",
          "content": "{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"返回数据成功。\",\n\"data\": {\n\"list\": [\n{\n\"id\": 57,\n\"nickname\": \"孤烟丶\",\n\"age\": 25,\n\"country\": \"中国\",\n\"province\": \"陕西省\",\n\"city\": \"西安市\",\n\"district\": \"新城区\",\n\"distance\": 1401785.0930982907,\n\"img\": \"http://127.0.0.1:9000/find/res/images/57/20200701/05.png\"\n},\n{\n\"id\": 51,\n\"nickname\": \"暮夏\",\n\"age\": 24,\n\"country\": \"中国\",\n\"province\": \"江苏省\",\n\"city\": \"南京市\",\n\"district\": \"秦淮区\",\n\"distance\": 1165271.2196834162,\n\"img\": \"http://127.0.0.1:9000/find/res/images/51/20200503/03.png\"\n},\n{\n\"id\": 22,\n\"nickname\": \"曲终人散\",\n\"age\": 22,\n\"country\": \"中国\",\n\"province\": \"广东省\",\n\"city\": \"深圳市\",\n\"district\": \"福田区\",\n\"distance\": 11630.023919958885,\n\"img\": \"http://127.0.0.1:9000/find/res/images/22/20200711/02.png\"\n},\n{\n\"id\": 10,\n\"nickname\": \"澡澡猫\",\n\"age\": 20,\n\"country\": \"中国\",\n\"province\": \"广东省\",\n\"city\": \"广州市\",\n\"district\": \"南沙区\",\n\"distance\": 50080.18515040895,\n\"img\": \"http://127.0.0.1:9000/find/res/images/10/20200722/01.png\"\n},\n{\n\"id\": 34,\n\"nickname\": \"白素杉\",\n\"age\": 24,\n\"country\": \"中国\",\n\"province\": \"北京市\",\n\"city\": \"北京市\",\n\"district\": \"朝阳区\",\n\"distance\": 1961017.910353171,\n\"img\": \"http://127.0.0.1:9000/find/res/images/34/20200612/01.png\"\n},\n{\n\"id\": 68,\n\"nickname\": \"丶倾城\",\n\"age\": 23,\n\"country\": \"中国\",\n\"province\": \"上海市\",\n\"city\": \"上海市\",\n\"district\": \"普陀区\",\n\"distance\": 1224663.7761815006,\n\"img\": \"http://127.0.0.1:9000/find/res/images/68/20200819/04.png\"\n},\n{\n\"id\": 90,\n\"nickname\": \"黑喵\",\n\"age\": 23,\n\"country\": \"中国\",\n\"province\": \"湖北省\",\n\"city\": \"武汉市\",\n\"district\": \"汉南区\",\n\"distance\": 873505.2168993158,\n\"img\": \"http://127.0.0.1:9000/find/res/images/90/20201007/01.png\"\n},\n{\n\"id\": 37,\n\"nickname\": \"无所谓\",\n\"age\": 24,\n\"country\": \"中国\",\n\"province\": \"北京市\",\n\"city\": \"北京市\",\n\"district\": \"海淀区\",\n\"distance\": 1960152.7662839654,\n\"img\": \"http://127.0.0.1:9000/find/res/images/37/20200626/01.png\"\n},\n{\n\"id\": 149,\n\"nickname\": \"洋洋12\",\n\"age\": 25,\n\"country\": \"中国\",\n\"province\": \"广东省\",\n\"city\": \"深圳市\",\n\"district\": \"坪山新区\",\n\"distance\": 46866.36032411066,\n\"img\": \"http://127.0.0.1:9000/find/res/images/149/20210610/1623324450475/28747ba1-d92b-42ef-9bf1-d18a50eecb88.jpg\"\n},\n{\n\"id\": 91,\n\"nickname\": \"桃子\",\n\"age\": 24,\n\"country\": \"中国\",\n\"province\": \"四川省\",\n\"city\": \"成都市\",\n\"district\": \"锦江区\",\n\"distance\": 1340238.1195277926,\n\"img\": \"http://127.0.0.1:9000/find/res/images/91/20201117/02.png\"\n}\n]\n}\n}",
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
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/user/{id}/look"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://127.0.0.1:8084/find/user/{id}/look-details",
    "title": "鹿可模块推荐用户详情接口",
    "version": "1.0.0",
    "group": "用户模块API",
    "name": "鹿可模块推荐用户详情",
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
            "field": "detailsUserId",
            "description": "<p>用户详情id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例",
          "content": "HTTP/1.1 OK\ncurl --insecure -X GET -v http://127.0.0.1:8084/find/user/35/look-details?detailsUserId=65 -H \"Content-Type: application/json;charset=UTF-8\"",
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
            "type": "long",
            "optional": true,
            "field": "id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "200",
            "type": "string[]",
            "size": "1..6",
            "optional": true,
            "field": "attacheList",
            "description": "<p>动态图片列表，最多6张</p>"
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
            "field": "constellation",
            "description": "<p>星座</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "gender",
            "description": "<p>性别，0-&gt;女，1-&gt;男</p>"
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
            "optional": true,
            "field": "industry",
            "description": "<p>行业</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "profession",
            "description": "<p>职业</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "country",
            "description": "<p>国家</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "province",
            "description": "<p>省份</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "city",
            "description": "<p>城市</p>"
          },
          {
            "group": "200",
            "type": "string",
            "optional": true,
            "field": "district",
            "description": "<p>区/县</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "HTTP/1.1 200 OK",
          "content": "{\n\"status\": 200,\n\"code\": 0,\n\"msg\": \"返回数据成功。\",\n\"data\": {\n\"id\": 65,\n\"attacheList\": [\n\"http://127.0.0.1:9000/find/res/images/65/20201113/04.png\",\n\"http://127.0.0.1:9000/find/res/images/65/20201113/05.png\",\n\"http://127.0.0.1:9000/find/res/images/65/20201107/09.png\",\n\"http://127.0.0.1:9000/find/res/images/65/20201107/010.png\",\n\"http://127.0.0.1:9000/find/res/images/65/20201024/012.png\",\n\"http://127.0.0.1:9000/find/res/images/65/20201024/06.png\"\n],\n\"nickname\": \"兰烬\",\n\"constellation\": \"金牛座\",\n\"gender\": \"0\",\n\"age\": 23,\n\"industry\": \"计算机/互联网/通信/电子\",\n\"profession\": \"后端开发工程师\",\n\"country\": \"中国\",\n\"province\": \"上海市\",\n\"city\": \"上海市\",\n\"district\": \"徐汇区\"\n}\n}",
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
          "content": "HTTP/1.1 404 404响应\n{\n\"status\": 404,\n\"code\": 200,\n\"msg\": \"接口未注册\",\n}",
          "type": "json"
        },
        {
          "title": "500错误",
          "content": "HTTP/1.1 500 500响应\n{\n\"status\": 500,\n\"code\": 205,\n\"msg\": \"服务器未响应\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/potato369/find/portal/controller/UserController.java",
    "groupTitle": "用户模块API",
    "sampleRequest": [
      {
        "url": "http://127.0.0.1:8084/find/user/{id}/look-details"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://127.0.0.1:8084/find/order/{id}/mobile/create",
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
          "content": "HTTP/1.1 OK\ncurl -v -X POST http://127.0.0.1:8084/find/order/1/mobile/create -H \"Content-Type:application/json;charset=utf-8\" -d '{\"pid\":1, \"mode\":0, \"ip\":\"127.0.0.1\"}'",
          "type": "json"
        },
        {
          "title": "请求示例02（支付宝支付）",
          "content": "HTTP/1.1 OK\ncurl -v -X POST http://127.0.0.1:8084/find/order/1/mobile/create -H \"Content-Type:application/json;charset=utf-8\" -d '{\"pid\":1, \"mode\":1, \"ip\":\"127.0.0.1\"}'",
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
        "url": "http://127.0.0.1:8084/find/order/{id}/mobile/create"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://127.0.0.1:8084/find/order/{id}/product/list",
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
          "content": "HTTP/1.1 OK\ncurl -v -X GET http://127.0.0.1:8084/find/order/1/product/list",
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
        "url": "http://127.0.0.1:8084/find/order/{id}/product/list"
      }
    ]
  }
] });
