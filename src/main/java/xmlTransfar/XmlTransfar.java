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

        // �h�L�������g�̑Ó��������؂��Ȃ�
        factory.setValidating(false);
        // xml���O��Ԃ��T�|�[�g����悤�Ɏw�肷��
        factory.setNamespaceAware(true);

        DocumentBuilder builder = factory.newDocumentBuilder();
        // XML�t�@�C���̎w��
        Document doc = builder.parse(
                "file:C:/github/halyami12/xmlTransfar/src/main/resources/xmlTransfar/hoge.xml");

        Element element = doc.getDocumentElement();
        // �e�m�[�h����ǂݍ���
        Node node = element.getParentNode();

        sb.append(convertToString(node));

        // �ǂݍ��݌���
        System.out.println("�ǂݍ��݌���");
        System.out.println(sb);

    }

    /**
     * �m�[�h�̗v�f�l�𕶎���ɕϊ����܂��B �m�[�h���Ƀ^�O���܂܂�Ă���ꍇ�A���̃^�O�͎������`����܂��B
     * @param node �ϊ��Ώۂ̕�����
     * @return �ϊ��㕶����
     * @throws TransformerException XML�K��Ɉᔽ���Ă���ꍇ�ɔ��������O
     */
    public static String convertToString(
            Node node) throws TransformerException {
        // �m�[�h��XML�Ƃ��Ē�`���܂�
        DOMSource source = new DOMSource(node);
        // �����񐶐��p�X�g���[��
        StringWriter swriter = new StringWriter();
        StreamResult result = new StreamResult(swriter);
        // XML�𕶎���ɕϊ����܂�
        transform(source, result);
        return swriter.toString();
    }

    /**
     * XML�ϊ��G���W���Ăяo���ł��B
     * @param source �ϊ��Ώۂ�XML
     * @param result �ϊ��㕶����
     * @throws TransformerException XML�K��Ɉᔽ���Ă���ꍇ�ɔ��������O
     */
    private static void transform(Source source,
            Result result) throws TransformerException {
        // �ϊ��G���W�����擾���܂�
        Transformer transformer = TransformerFactory.newInstance()
                .newTransformer();
        // XML�ϊ��̃��[����ݒ肵�܂�
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        // �ϊ�
        transformer.transform(source, result);
    }
}
