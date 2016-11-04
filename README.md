调用参数说明：<table class="table table-hover">
            <thead></thead>
            <tbody><tr>
                <td>参数名</td>
                <td nowrap="">类型</td>
                <td class="upsm">说明</td>
            </tr>
            <tr>
                <td>image</td>
                <td nowrap="">String | File</td>
                <td>
                    <p>
                        【image/text 二选一】图片识别接口参数。
                        <br>
                        1、支持传图片URL或者直接post图片文件，不过两者不能混在同一请求中；
                        <br>
                        2、支持多个image参数，也就是上传多个图片文件；
                        <br>
                        3、支持压缩包格式：.zip；
                        <br>
                        4、只支持对静态图像的处理，如：.png、.jpg、.jpeg、.bmp，不支持动态图像的处理，如：.gif；
                        <br>
                        5、为了加快网络传输速度，在直接post文件时，建议对图片进行压缩处理，压缩到256*256即可。
                    </p>
                </td>
            </tr>
            <tr>
                <td>text</td>
                <td nowrap="">String</td>
                <td>
                    <p>
                        【image/text 二选一】文本识别接口参数。
                        <br>
                        1、支持多个text参数，每个text独立识别。
                    </p>
                </td>
            </tr>
            <tr>
                <td>tag</td>
                <td>String</td>
                <td>
                    【可选】用于给图片或文本附加额外信息（比如：直播客户可能传房间号，或者主播ID信息）。方便后续根据tag搜索到相关的图片或文本。<br>1、支持多个tag参数，和image或text参数一一对应；<br>2、如果tag只有一个，或者tag数量少于image或text，缺省用最后一个tag补全（尾补全）。
                </td>
            </tr>
             <tr>
                <td>taskId</td>
                <td>String</td>
                <td>
                    用于选择任务类型，如色情、广告<br>支持多个taskId参数
                </td>
            </tr>
            <tr>
                <td>timestamp</td>
                <td nowrap="">Number</td>
                <td>当前的服务器的Unix时间戳。</td>
            </tr>
            <tr>
                <td>nonce</td>
                <td nowrap="">Number</td>
                <td>随机数。</td>
            </tr>
            <tr>
                <td>signature</td>
                <td nowrap="">String</td>
                <td>
                    <p>我们采用的数字证书签名算法是：<b class="text-danger">"RSA-SHA256"</b>，签名输出类型是：<b class="text-danger">"base64"</b>，具体步骤如下：
                        <br>
                        1、所有参与签名的参数为：secretId，timestamp，nonce，用英文半角逗号“,”相连，得到sign_string；
                        <br>
                        2、利用您的私钥，使用算法<b class="text-danger">"RSA-SHA256"</b>签名，输出为 <b class="text-danger">"base64"</b> 格式，得到参数signature的值。
                    </p>                    
                </td>
            </tr>
        </tbody></table>
        <br/>
返回结果说明：<table class="table table-hover">
            <tbody><tr>
                <td>signature</td>
                <td nowrap="">String</td>
                <td>
                    我们采用的数字证书签名算法是：<b class="text-danger">"RSA-SHA256"</b>，签名输出类型是：<b class="text-danger">"base64"</b>,按如下步骤认证：<br>
                    1、收到的JSON数据经过JSON.parse()，得到JSON对象，记为data；<br>
                    2、data含两个字段：signature、json；signature是数字签名，json是真正的有效数据的字符串格式；<br>
                    3、用signature对json进行签名认证,算法：<b class="text-danger">RSA-SHA256</b>，输出类型：<b class="text-danger">base64</b>，得到认证结果；<br>
                    4、认证通过后，对json进行JSON.parse()，得到JSON数据，详见"json"字段说明。
                </td>
            </tr>
            <tr>
                <td>json</td>
                <td>String</td>
                <td>json 字段经过JSON.parse(json)后，得到具体的结果，含：
                    <p>
                        <strong>code</strong>：0：调用成功;1：授权失败；2：模型ID错误；3：没有上传文件；4：API版本号错误；5：API版本已弃用；6：secretId 错误；7：任务Id错误，您的secretId不能调用该任务；8：secretId状态异常；9：尚未上传证书；10：pipeId 错误；11：没有callback参数；12：时间参数错误；13：没有配置计算任务或者都已关闭；14：图片错误：404、400、下载失败、过大等；100：服务器错误；101：未知错误。
                    </p>
                    <p><strong>message</strong>：与code相关的文本信息</p>
                    <p><strong>timestamp</strong>：当前的服务器的Unix时间戳。</p>
                    <p><strong>nonce</strong>：随机数。</p>
                </td>
            </tr>
</tbody></table>

        
