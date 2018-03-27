package xmlTransfar;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlTransfar {

    public static void main(String[] args) throws Exception {

        StringBuffer sb = new StringBuffer();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // ドキュメントの妥当性を検証しない
        factory.setValidating(false);
        // xml名前空間をサポートするように指定する
        factory.setNamespaceAware(true);

        DocumentBuilder builder = factory.newDocumentBuilder();
        // XMLファイルの指定
        Document doc = builder.parse(
                "file:C:/github/halyami12/xmlTransfar/src/main/resources/xmlTransfar/hoge.xml");

        Element element = doc.getDocumentElement();
        // 親ノードから読み込む
        Node node = element.getParentNode();

        sb.append(convertToString(node));

        // 読み込み結果
        System.out.println("読み込み結果");
        System.out.println(sb);

    }

    /**
     * ノードの要素値を文字列に変換します。 ノード内にタグが含まれている場合、そのタグは自動整形されます。
     * @param node 変換対象の文字列
     * @return 変換後文字列
     * @throws TransformerException XML規約に違反している場合に発生する例外
     */
    public static String convertToString(
            Node node) throws TransformerException {
        // ノードをXMLとして定義します
        DOMSource source = new DOMSource(node);
        // 文字列生成用ストリーム
        StringWriter swriter = new StringWriter();
        StreamResult result = new StreamResult(swriter);
        // XMLを文字列に変換します
        transform(source, result);
        return swriter.toString();
    }

    /**
     * XML変換エンジン呼び出しです。
     * @param source 変換対象のXML
     * @param result 変換後文字列
     * @throws TransformerException XML規約に違反している場合に発生する例外
     */
    private static void transform(Source source,
            Result result) throws TransformerException {
        // 変換エンジンを取得します
        Transformer transformer = TransformerFactory.newInstance()
                .newTransformer();
        // XML変換のルールを設定します
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        // 変換
        transformer.transform(source, result);
    }
}
