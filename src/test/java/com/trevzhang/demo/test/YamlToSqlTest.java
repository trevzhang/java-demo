package com.trevzhang.demo.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;

/**
 * This class reads a YAML file and generates SQL INSERT statements.
 * Author: Haruki
 * Since: 2024/12/17
 */
@Slf4j
public class YamlToSqlTest {

    @Test
    public void testGenerateSql() {
        String yamlPath = "src/test/resources/com/trevzhang/demo/test/test_load.yaml";
        try {
            JsonNode rootNode = loadYaml(yamlPath, JsonNode.class);
            if (rootNode != null) {
                generateSqlStatements(rootNode);
            }
        } catch (IOException e) {
            log.error("Error loading YAML", e);
        }
    }

    public <T> T loadYaml(String yamlPath, Class<T> clazz) throws IOException {
        String content = readFile(yamlPath);
        if (content == null) return null;

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        StringReader reader = new StringReader(content);
        T loadObj = null;
        try {
            loadObj = objectMapper.readValue(reader, clazz);
            return loadObj;
        } catch (IOException e) {
            log.error("Error parsing YAML", e);
            return null;
        }
    }

    private static String readFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            log.error("File not found: {}", path);
            return null;
        }

        FileInputStream input = new FileInputStream(file);
        byte[] bytes = new byte[input.available()];
        int offset = input.read(bytes);
        if (offset < input.available()) {
            throw new RuntimeException("Error reading file");
        }
        input.close();

        return new String(bytes);
    }

    private void generateSqlStatements(JsonNode rootNode) {
        JsonNode commonConfigNode = rootNode.path("common-config");
        StringBuilder sqlBuilder = new StringBuilder();

        Iterator<String> gameBaseIds = commonConfigNode.fieldNames();
        while (gameBaseIds.hasNext()) {
            String gameBaseId = gameBaseIds.next();
            JsonNode allowListNode = commonConfigNode.path(gameBaseId).path("allow-list");

            for (JsonNode uidNode : allowListNode) {
                String uid = uidNode.asText();
                String sql = String.format("INSERT INTO `game_webpay_whitelist` (`game_base_id`, `uid`, `test_server_id`, `deleted`) VALUES (%s, '%s', 0, 0);", gameBaseId, uid);
                sqlBuilder.append(sql).append("\n");
            }
        }

        log.info("Generated SQL:\n{}", sqlBuilder.toString());
    }
}
